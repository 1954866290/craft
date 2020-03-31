package com.zkwp.administration.controller;

import com.alibaba.fastjson.JSONObject;
import com.zkwp.administration.service.LoginService;
import com.zkwp.api.controller.BaseController;
import com.zkwp.api.utils.CommonListResult;
import com.zkwp.api.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @auther zhangkun
 * @date 2020/3/14 21:36
 **/
@Controller
public class LoginController extends BaseController {

    @Autowired
    LoginService loginService;

    @RequestMapping("/login")
    public String login(){
       return "login";
    }


    @RequestMapping(value = "/doLogin",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String doLogin(HttpServletRequest request) throws  Exception{
        Map param = getParameterMap(request);
        JSONObject jsonObject = new JSONObject();
        String username = StringUtil.objToString(param.get("loginId"));
        String password = StringUtil.objToString(param.get("password"));
        if(username.equals("admin")&&password.equals("admin")){
            request.getSession().setAttribute("userName",username);
            return returnSuccess(param,jsonObject);
        }else{
            return returnSuccess(param,jsonObject);
        }
    }

    @RequestMapping("/doLogout")
    public CommonListResult doLogout(HttpServletRequest request){
        request.getSession().removeAttribute("userName");
        return CommonListResult.success(null);
    }
}
