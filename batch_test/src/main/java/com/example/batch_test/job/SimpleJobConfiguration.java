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
