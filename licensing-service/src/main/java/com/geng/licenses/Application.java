package com.geng.licenses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//告诉Spring Boot框架,这是项目的引导类
@SpringBootApplication
//激活Spring DiscoveryClient
@EnableDiscoveryClient
//在代码中启用Feign客户端
@EnableFeignClients
@RefreshScope
public class Application {
    //在使用支持Ribbon的RestTemplate时，并不需要用到@EnableDiscoveryClient和@EnableFeignClients，因此可以将它们移除
    //告诉Spring Cloud创建一个支持Ribbon的RestTemplate类
    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        //调用以启动整个Spring Boot服务
        SpringApplication.run(Application.class, args);
    }
}
