package com.zkwp.consumer.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @auther zhangkun
 * @date 2020/2/22 12:38
 **/
@Configuration
public class ConfigBean {

    @Bean
    @LoadBalanced
    public RestTemplate getTemplate() {  //RestTemplate
        return new RestTemplate();
    }


}
