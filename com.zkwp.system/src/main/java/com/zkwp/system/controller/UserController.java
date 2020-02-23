package com.zkwp.system.controller;

import com.zkwp.system.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @auther zhangkun
 * @date 2020/2/16 11:40
 **/
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    EmailService emailService;

    @RequestMapping("/sendEmail")
    public void sendEmail(){
        for (int i = 0 ; i<10;i++)
        emailService.sendSimpleMail("2647808433@qq.com","小宝贝","爸爸爱你");
    }


}
