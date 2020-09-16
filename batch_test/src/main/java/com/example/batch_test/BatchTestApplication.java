package com.example.batch_test;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Spring Batch 기능을 실행하고 @Configuration 클래스에서 배치 작업을 설정하기 위한 기본 구성을 제공한다.
@EnableBatchProcessing
@SpringBootApplication
public class BatchTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(BatchTestApplication.class, args);
    }
}
