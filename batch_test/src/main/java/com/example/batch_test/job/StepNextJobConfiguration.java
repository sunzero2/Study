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

@Slf4j
@Configuration
@RequiredArgsConstructor
public class StepNextJobConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job stepNextJob() {
        return jobBuilderFactory.get("stepNextJob")
                // step1 -> step2 -> step3 순으로 실행
                .start(step1())
                // SimpleJobBuilder.next() : Step을 연결시킬 때 사용.
                // 이전 단계(step1)가 성공한 경우 해당 작업(step2)을 실행
                // 하지만 "성공했을 경우"에만 실행된다는 단점이 있음. 이를 보완하기 위한 on(), to()가 있음(StepNextConditionalJobConfiguraion에서 설명)
                .next(step2())
                .next(step3())
                .build();
    }

    private Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet(((contribution, chunkContext) -> {
                    log.info(">>>>> This is Step1");
                    return RepeatStatus.FINISHED;
                }))
                .build();
    }

    private Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet(((contribution, chunkContext) -> {
                    log.info(">>>>> This is Step2");
                    return RepeatStatus.FINISHED;
                }))
                .build();
    }

    private Step step3() {
        return stepBuilderFactory.get("step3")
                .tasklet(((contribution, chunkContext) -> {
                    log.info(">>>>> This is Step3");
                    return RepeatStatus.FINISHED;
                }))
                .build();
    }
}
