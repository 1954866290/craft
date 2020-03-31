package com.zkwp.administration.controller;

import com.zkwp.api.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @auther zhangkun
 * @date 2020/3/15 16:30
 **/
@Controller
public class IndexController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/index")
    public String index(){
        return  "index";
    }


}
