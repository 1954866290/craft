package com.zkwp.administration.configBean;

import com.zkwp.administration.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @auther zhangkun
 * @date 2020/3/14 21:42
 **/
@Configuration
public class LoginConfig  implements WebMvcConfigurer  {

    @Autowired
    LoginInterceptor loginInterceptor;

    public  void addInterceptors(InterceptorRegistry interceptorRegistry){
        InterceptorRegistration interceptorRegistration = interceptorRegistry.addInterceptor(loginInterceptor);
        interceptorRegistration.addPathPatterns("/**");
        interceptorRegistration.excludePathPatterns("/**/*.html",
                                                    "/**/*.css",
                                                    "/**/*.js");
    }
}
