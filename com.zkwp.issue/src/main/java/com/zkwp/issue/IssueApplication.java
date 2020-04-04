package com.zkwp.issue;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @auther zhangkun
 * @date 2020/2/15 10:44
 **/
@SpringBootApplication(scanBasePackages = "com.zkwp.issue")
@EnableEurekaClient
@EnableScheduling
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableFeignClients
public class IssueApplication {
    public static void main(String[] args) {
        SpringApplication.run(IssueApplication.class);
    }
}
