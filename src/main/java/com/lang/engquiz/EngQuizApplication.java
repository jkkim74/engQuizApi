package com.lang.engquiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients  // Feign 클라이언트를 활성화
public class EngQuizApplication {

    public static void main(String[] args) {
        SpringApplication.run(EngQuizApplication.class, args);
    }

}
