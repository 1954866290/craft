package com.zkwp.consumer.controller.system;

import com.zkwp.api.bean.User;
import com.zkwp.api.utils.CommonResult;
import com.zkwp.api.utils.RestUtil;
import com.zkwp.api.utils.StringUtil;
import com.zkwp.consumer.service.system.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @auther zhangkun
 * @date 2020/4/11 15:26
 **/

@Controller
@RequestMapping(value = "/system/login")
public class LoginController {
    private Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    LoginService loginService;


    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping(value = "/sendCode")
    @ResponseBody
    public CommonResult sendCode(HttpServletRequest request) {
        Map params = RestUtil.getParameterMap(request);
        String ip = getIpAddr(request);
        try {
            return loginService.sendCode(params, ip);
        } catch (Exception e) {
            log.error("LoginController::sendCode throws exception", e);
            return CommonResult.failed();
        }
    }

    @RequestMapping(value = "/dologin")
    @ResponseBody
    public CommonResult dologin(HttpServletRequest request, HttpSession session) {
        Map params = RestUtil.getParameterMap(request);
        String ip = getIpAddr(request);
        try {
            CommonResult commonResult = loginService.doLogin(params, ip);
            if(commonResult.getstatus().equals("200")){
                String userid = StringUtil.objToString(commonResult.getData());
                session.setAttribute("userid",userid);
            }
            return commonResult;
        } catch (Exception e) {
            log.error("LoginController::sendCode throws exception", e);
            return CommonResult.failed();
        }
    }

    @RequestMapping(value = "/dologout")
    public String logout(HttpSession session){
        String userid = StringUtil.objToString(session.getAttribute("userid"));
        session.invalidate();//使Session变成无效，及用户退出
        return "logout";
    }

    private static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (StringUtil.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (StringUtil.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            return request.getRemoteAddr();
        }
    }


}
