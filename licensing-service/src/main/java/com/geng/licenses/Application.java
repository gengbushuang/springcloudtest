package com.geng.licenses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//告诉Spring Boot框架,这是项目的引导类
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        //调用以启动整个Spring Boot服务
        SpringApplication.run(Application.class, args);
    }
}
