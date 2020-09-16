package com.example.batch_test.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// on() 과 to() 를 사용하기 위한 기본 지식
// ExitStatus vs BatchStatus
// BatchStatus : Job 또는 Step의 실행 결과를 Spring에서 기록할 때 사용하는 Enum
//               COMPLETED, STARTING, STARTED, STOPPING, STOPPED, FAILED, ABANDONED, UNKNOWN의 상태값을 가진다.
// ExitStatus : Step의 실행 후 상태. Enum이 아니다.
//              BatchStatus와 같은 상태값을 가진다.

// ** Enum 이란?
// 열거형이라고 불리며, 서로 연관된 상수들의 집합을 의미한다.
// Enum의 장점 : 코드가 단순 -> 가독성이 높음 / 인스턴스 생성과 상속을 방지 -> 상수값의 타입안정성 보장
//               새로운 상수들의 타입을 정의함으로써 정의한 타입 이외의 타입을 가진 데이터 값을 컴파일 시 체크
// 기존 상수 정의 방법 : final static stirng
//      --> 다른 클래스에서 이 상수를 변수에 할당했다가 다른 문자열로 바꿔도 Java는 알아채지 못한다.
//          Enum은 열거해둔 상수타입을 final 객체로 자동 할당하므로 이러한 문제를 방지할 수 있다.

@Slf4j
@Configuration
@RequiredArgsConstructor
public class StepNextConditionalJobConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job stepNextConditionalJob() {
        // step1 실패 시나리오 : step1 -> step3
        // step1 성공 시나리오 : step1 -> step2 -> step3

        // SimpleJobBuilder.on() : 캐치할 ExitStatus 지정. *일 경우 모든 ExitStatus가 지정된다.
        //                         캐치하는 상태값이 ExitStatus이기 때문에 이를 조정해야 한다.
        // TransitionBuilder.to() : 다음으로 실행할 Step 지정
        // FlowBuilder.from() : start()와 동일한 뜻. 그러나 이미 start()한 Step의 경우 from()을 사용해야 한다.
        // TransitionBuilder.end() : FlowBuilder를 반환하는 end()와 FlowBuilder를 종료하는 end()가 있음
        //      on 뒤에 있는 end : FlowBuilder를 반환하는 end -> 계속해서 from을 이어갈 수 있음
        //      build 앞에 있는 end : FlowBuilder를 종료하는 end
        return jobBuilderFactory.get("stepNextConditionalJob")
                .start(conditionalJobStep1())
                    // FAILED일 경우 Step3으로 이동한다.
                    .on("FAILED").to(conditionalJobStep3())
                    // Step3의 결괏값에 상관없이 종료한다.
                    .on("*").end()
                // 위에서 FAILED만 지정해둔 상태
                .from(conditionalJobStep1())
                    // FAILED 외 모든 경우 step2로 이동
                    .on("*").to(conditionalJobStep2())
                    // Step2가 정상 종료되면 Step3으로 이동
                    .next(conditionalJobStep3())
                    // Step3의 결괏값에 상관없이 종료한다.
                    .on("*").end()
                .end()
                .build();
    }

    @Bean
    public Step conditionalJobStep1() {
        return stepBuilderFactory.get("step1")
                .tasklet((contribution, chunkContext) -> {
                    log.info(">>>>> This is stepNextConditionalJob Step1");
                    // on이 캐치할 수 있는 setExitStatus
                    //contribution.setExitStatus(ExitStatus.FAILED);
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step conditionalJobStep2() {
        return stepBuilderFactory.get("conditionalJobStep2")
                .tasklet((contribution, chunkContext) -> {
                    log.info(">>>>> This is stepNextConditionalJob Step2");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step conditionalJobStep3() {
        return stepBuilderFactory.get("conditionalJobStep3")
                .tasklet((contribution, chunkContext) -> {
                    log.info(">>>>> This is stepNextConditionalJob Step3");
                    return RepeatStatus.FINISHED;
                }).build();
    }
}
