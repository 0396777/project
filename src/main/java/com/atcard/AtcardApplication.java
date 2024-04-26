package com.atcard;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@EnableAsync
@SpringBootApplication(scanBasePackages = "com.atcard")
@MapperScan("com.atcard.mappers")
//@EnableTransactionManagement
//@EnableScheduling
public class AtcardApplication {
    public static void main(String[] args) {
        SpringApplication.run(AtcardApplication.class);
    }
}