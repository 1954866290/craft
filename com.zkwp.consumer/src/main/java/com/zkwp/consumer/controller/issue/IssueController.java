package com.zkwp.consumer.controller.issue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @auther zhangkun
 * @date 2020/2/22 12:06
 **/
@Controller
@RequestMapping(value = "/issue")
public class IssueController {

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

    @RequestMapping(value = "/issueCenter")
    public String issueCenter(){
        return "issueCenter";
    }

    @RequestMapping(value = "/issueCenter/issue")
    @ResponseBody
    public String issue(){
        return "";
    }

}

