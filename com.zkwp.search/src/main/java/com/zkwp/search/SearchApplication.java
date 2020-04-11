package com.zkwp.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @auther zhangkun
 * @date 2020/2/15 10:53
 **/
@SpringBootApplication(scanBasePackages = "com.zkwp.search")
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableFeignClients
public class SearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class);
    }
}
