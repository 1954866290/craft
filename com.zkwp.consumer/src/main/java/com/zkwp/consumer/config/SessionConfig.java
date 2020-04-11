package com.zkwp.consumer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @auther zhangkun
 * @date 2020/4/11 23:27
 **/
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3*60*60)
public class SessionConfig {
}
