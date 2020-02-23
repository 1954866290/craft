package com.zkwp.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @auther zhangkun
 * @date 2020/2/23 15:54
 **/
@SpringBootApplication(scanBasePackages = {"com.zkwp.system"})
@EnableEurekaClient
public class SystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class);
    }
}
