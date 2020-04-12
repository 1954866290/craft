package com.zkwp.administration.controller.rest;

import com.zkwp.administration.service.TypeService;
import com.zkwp.api.bean.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @auther zhangkun
 * @date 2020/4/11 11:04
 **/

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = { "/feign-api" })
public class RestController {

    @Autowired
    TypeService typeService;

    @PostMapping(value = "/Type/getTypeListByPCode")
    List<Type> getTypeListByPCode(@RequestParam("pcode")String pcode){
       return typeService.getTypeListByPCode(pcode);
    }


}
