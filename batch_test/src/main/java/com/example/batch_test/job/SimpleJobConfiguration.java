package com.example.batch_test.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Job & Step
// Job : 하나의 배치 작업 단위. Job 안에는 여러 Step이 존재한다.
// Step : 실제 Batch 작업을 수행하는 역할. 실제로 처리하고자 하는 기능과 설정을 모두 포함하는 장소
//      Step 안에는 여러 Step이 존재하고, Step 안에 Tasklet 혹은 (Reader & Processor & Writer) 묶음이 존재한다.
//      Tasklet과 (Reader & Processor & Writer)는 같은 레벨이다.
//      즉, Reader & Processor가 끝나고 Tasklet으로 마무리 할 수 없다.
// Reader : 데이터를 읽어온다. (읽어올 수 있는 데이터 유형 : 입력 데이터, 파일, Database, Java Message Service 등 다른 소스)
//          가장 대표적인 구현체가 JdbcPagingItemReader 다. ItemReader 와 ItemStream 인터페이스를 구현하고 있다.
//          ItemReader 는 read()만 가지고 있음. ItemStream 은 open(), update(), close()를 가지고 있음. -> ItemStream 은 주기적으로 상태를 저장하고 오류가 발생하면 해당 상태에서 복원한다. ItemReader 의 상태를 저장하고 실패한 곳에서 다시 실행할 수 있게 해주는 역할을 한다.
//          CursorItemReader 와 PagingItemReader 가 있다.
//          Cursor 는 Database 와 Connection 을 맺은 후 Cursor 를 한 칸씩 옮기면서 데이터를 가져온다. 하나의 Connection 으로 Batch 가 끝날 때까지 사용하기 때문에 Database 와 SocketTimeout 을 큰 값으로 설정해야 한다.
//          Paging 은 limit, offset 등을 이용해서 실제 쿼리에서 얼만큼 가져올지를 정할 수 있다.(Spring Batch 에서는 Page Size 에 맞게 자동으로 생성해준다.) 한 페이지를 읽을 때마다 Connection 을 맺고 끊기 때문에 아무리 많은 데이터라도 Timeout 과 부하없이 실행할 수 있다.
//              다만, 쿼리는 개별적으로 실행되기 때문에 각 페이지마다 새로운 페이지를 실행한다. 그래서 페이징 시 결과를 정렬하는 것이 중요하다. (데이터 결과의 순서가 보장되는 order by가 권장된다.)
//          Batch 수행 시간이 오래 걸리는 경우에는 PagingItemReader 를 사용하는게 낫다.
// Processor : Reader 에서 넘겨준 데이터 개별건을 가공/처리해준다. (필수 아님 Reader 에서 읽은 데이터를 크게 변경할 로직이 없다면 processor 를 제외해도 된다.)
//             변환 : Reader 에서 읽은 데이터를 원하는 타입으로 변환해서 Writer 에 넘겨줄 수 있다.
//             필터 : Reader 에서 넘겨준 데이터를 Writer 로 넘겨줄 것인지를 결정. null 을 반환하면 Writer 에 전달되지 않음
//             Processor 에는 두 개의 제네릭이 필요하다. Reader 에서 받은 데이터 타입과 Writer 에 보낼 데이터 타입
// Writer : 데이터를 출력한다. Spring Batch 가 처음 나왔을 때는 Writer 도 Reader 와 마찬가지로 item 을 하나씩 다뤘다. 그러나 Chunk 도입으로 인해 Chunk 단위로 묶인 item List 를 다루게 됐다.
//          Writer 는 Chunk 단위의 마지막 단계이기 때문에 Database 를 출력할 때는 항상 마지막에 Flush 를 해줘야 한다.
//          JdbcBatchItemWriter : JDBC 의 Batch 기능을 사용하여 한 번에 Database 로 전달하여 Database 내부에서 쿼리들이 실행되도록 한다.
//                                Application 과 Database 간에 데이터를 주고받는 횟수를 최소화하여 성능 향상을 꾀한다.
//                                JdbcBatchItemWriter 의 제네릭은 Reader 에서 넘겨주는 값의 타입이여야 한다.
//          JpaItemWriter : Writer 에 전달하는 데이터가 Entity 클래스라면 JpaItemWriter 를 사용하면 된다.
//                          JPA 를 사용하기 때문에 영속성 관리를 위해 EntityManager 를 할당해줘야 한다. -> gradle 에 spring-boot-starter-data-jpa 를 등록하면 Entity Manager 가 Bean 으로 자동 생성된다. DI 코드만 추가하면 된다.
//                          JdbcBatchItemWriter 와의 차이점은 Processor 가 추가된 것이다. Entity 를 읽어서 전달해주기 위함이다.
//                          JpaItemWriter 의 제네릭은 Entity 다.
@Slf4j
@RequiredArgsConstructor // 초기화 되지 않은 final 필드나 @NonNull이 붙은 필드에 생성자를 생성한다.
// 스프링 컨테이너에게 해당 클래스가 Bean 구성 클래스임을 알려주는 것이다.
// Spring Batch의 모든 Job은 @Configuration으로 등록해서 사용한다.
@Configuration
public class SimpleJobConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job simpleJob() {
        // Job 내부에서 Step 간에 순서 혹은 처리 흐름을 제어한다.

        // JobBuilderFactory.get() : JobBuilder를 생성하고 초기화한다.
        // 인수로 전달하는 name은 Job의 이름이 된다.
        return jobBuilderFactory.get("simpleJob")
                // Step을 실행한다.
                .start(simpleStep3())
                .next(simpleStep1("2020-09-17"))
                .build();
    }

    @Bean

    // JobParameter : Spring Batch에서는 외부 혹은 내부에서 파라미터를 받아 여러 Batch 컴포넌트에서 사용할 수 있도록 지원
    //                항상 Spring Batch 전용 Scope를 선언해야 함 (@StepScope, @JobScope)
    //                사용할 수 있는 타입 : Double, Long, Date, String
    //                @Value를 통해서 생성 가능 -> @StepScope, @JobScope Bean을 생성할 때만 JobParameter가 생성된다.
    // JobScope & StepScope
    // @JobScope : jobParameters, jobExecutionContext를 SpEL로 사용 가능
    //             Step 선언문에서 사용 가능
    //             지정된 Job의 실행시점에 해당 컴포넌트를 Bean으로 생성
    // @StepScope : jobParameters, jobExecutionContext, stepExecutionContext 등을 SpEL로 사용가능
    //              Tasklet, ItemReader, ItemWriter, ItemProcessor에서 사용 가능
    //              지정된 Step의 실행시점에 해당 컴포넌트를 Bean으로 생성
    @JobScope
    public Step simpleStep1(@Value("#{jobParameters[requestDate]}") String requestDate) {
        return stepBuilderFactory.get("simpleStep1")
                // StepBuilder.tasklet() : Step 안에서 단일로 수행될 커스텀한 기능을 선언할 때 사용한다.
                .tasklet(((contribution, chunkContext) -> {
                    log.info(">>>>> This is Step 1");
                    log.info(">>>>> requestDate = {}", requestDate);
                    // RepeatStatus : Step의 상태를 나타냄
                    // FINISHED : 처리가 완료됐음을 나타냄
                    // CONTINUABLE : 처리를 계속할 수 있음을 나타냄
                    return RepeatStatus.FINISHED;
                })).build();
    }

    private Step simpleStep2() {
        return stepBuilderFactory.get("simpleStep2")
                .tasklet(((contribution, chunkContext) -> {
                    log.info(">>>>> This is Step2");
                    return RepeatStatus.FINISHED;
                })).build();
    }

    private final SimpleJobTasklet tasklet;

    public Step simpleStep3() {
        log.info(">>>>>>> definition simpleStep3");
        return stepBuilderFactory.get("simpleStep3")
                .tasklet(tasklet)
                .build();
    }
}
