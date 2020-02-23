package com.zkwp.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * @auther zhangkun
 * @date 2020/2/18 21:44
 **/
@SpringBootApplication(scanBasePackages = "com.zkwp",exclude= {DataSourceAutoConfiguration.class})
public class ApiApplication {
    public static void main(String[] args) {

        SpringApplication.run(ApiApplication.class);
    }
}
