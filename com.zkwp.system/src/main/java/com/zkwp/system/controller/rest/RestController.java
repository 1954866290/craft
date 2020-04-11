package com.zkwp.system.controller.rest;

import com.zkwp.api.utils.CommonResult;
import com.zkwp.system.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @auther zhangkun
 * @date 2020/4/11 15:45
 **/
@org.springframework.web.bind.annotation.RestController
@RequestMapping(value ="/feign-api")
public class RestController {

    @Autowired
    LoginService loginService;


    @PostMapping(value = "/login/sendCode")
    public CommonResult sendCode(@RequestParam("type")String type, @RequestParam("code")String code, @RequestParam("ip")String ip){
       try{
           return loginService.sendCode(type,code,ip);
       }catch (Exception e){
           return CommonResult.failed();
       }
    }

    @PostMapping(value = "/login/doLogin")
    public CommonResult doLogin(@RequestParam("type")String type,@RequestParam("code")String code,@RequestParam("verify")String verify,@RequestParam("ip")String ip){
        try{
            return loginService.doLogin(type,code,verify,ip);
        }catch (Exception e){
            return CommonResult.failed();
        }
    }

}
