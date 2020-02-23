package com.zkwp.consumer.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

/**
 * @auther zhangkun
 * @date 2020/2/16 15:01
 **/
@Controller
@RequestMapping(value = "/system")
public class IndexController {

    @Value("${SYSTEM_REST_URL_PREFIX}")
    private  String SYSTEM_REST_URL_PREFIX ;
    @Value("${ISSUE_REST_URL_PREFIX}")
    private  String ISSUE_REST_URL_PREFIX ;
    @Value("${CHAT_REST_URL_PREFIX}")
    private  String CHAT_REST_URL_PREFIX ;
    @Value("${ADMINISTRATION_REST_URL_PREFIX}")
    private  String ADMINISTRATION_REST_URL_PREFIX ;
    @Value("${SEARCH_REST_URL_PREFIX}")
    private  String SEARCH_REST_URL_PREFIX ;

    @Autowired
    private RestTemplate restTemplate;


    @RequestMapping("/index")
    public String index(){
        return "index";
    }

}
