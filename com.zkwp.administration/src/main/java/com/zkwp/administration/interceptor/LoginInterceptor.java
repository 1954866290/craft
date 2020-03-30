package com.zkwp.administration.interceptor;

import com.zkwp.api.bean.User;
import org.springframework.amqp.core.Exchange;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @auther zhangkun
 * @date 2020/3/14 21:45
 **/
@Component
public class LoginInterceptor implements HandlerInterceptor {

    public boolean preHandler(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception{
            String user = (String)request.getSession().getAttribute("userName");
            if(user == null){
                request.getRequestDispatcher("/login.html").forward(request, response);
                return false;
            }else{
                return true;
            }
    }
}
