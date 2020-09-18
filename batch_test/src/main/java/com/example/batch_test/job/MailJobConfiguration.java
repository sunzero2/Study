package com.example.batch_test.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class MailJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final JobLauncher jobLauncher;

    @Bean
    public void handle() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addDate("requestDate", new Date())
                    .toJobParameters();
            jobLauncher.run(jobTest(), jobParameters);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    @Bean
    public Job jobTest() {
        return jobBuilderFactory.get("jobTest")
                .start(stepTest1(null))
                .build();
    }

    @Bean
    @JobScope
    //@Value("#{jobParameters[requestDate]}")
    public Step stepTest1(@Value("#{jobParameters[requestDate]}") Date requestDate) {
        return stepBuilderFactory.get("stepTest1")
                .tasklet((contribution, chunkContext) -> {
                    log.info(String.valueOf(contribution));
                    log.info(String.valueOf(chunkContext));
                    log.info(String.valueOf(requestDate));
                    return RepeatStatus.FINISHED;
                }).build();
    }
}
