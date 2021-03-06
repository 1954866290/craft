package com.zkwp.wechat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @auther zhangkun
 * @date 2020/4/4 12:50
 **/
@SpringBootApplication(scanBasePackages = "com.zkwp.wechat")
@EnableHystrix
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableFeignClients
public class WeChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeChatApplication.class);
    }
}
