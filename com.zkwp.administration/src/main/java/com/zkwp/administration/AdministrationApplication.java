package com.zkwp.administration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @auther zhangkun
 * @date 2020/2/14 20:52
 **/
@SpringBootApplication(scanBasePackages = "com.zkwp.administration")
@EnableEurekaClient
public class AdministrationApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdministrationApplication.class);
    }
}