package com.zkwp.consumer.controller.system;

import com.alibaba.fastjson.JSONObject;
import com.zkwp.api.bean.OutputObject;
import com.zkwp.api.bean.User;
import com.zkwp.api.utils.RestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @auther zhangkun
 * @date 2020/2/16 11:10
 **/
@Controller
@RequestMapping(value = "/system")
public class UserController {
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

    @RequestMapping(value = "/user/add")
    public boolean addUser(User user){
        return restTemplate.postForObject(SYSTEM_REST_URL_PREFIX+"/user/addUser",user,Boolean.class);
    }

}
