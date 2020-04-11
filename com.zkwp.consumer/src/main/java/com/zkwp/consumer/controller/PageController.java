package com.zkwp.consumer.controller;

import com.zkwp.api.bean.SystemType;
import com.zkwp.api.bean.Type;
import com.zkwp.api.utils.CommonListResult;
import com.zkwp.api.utils.RestUtil;
import com.zkwp.consumer.feign.AdministrationFeignService;
import com.zkwp.consumer.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @auther zhangkun
 * @date 2020/4/11 10:57
 **/
@Controller
@RequestMapping("")
public class PageController {


    @Value("${SYSTEM_REST_URL_PREFIX}")
    private String SYSTEM_REST_URL_PREFIX;
    @Value("${ISSUE_REST_URL_PREFIX}")
    private String ISSUE_REST_URL_PREFIX;
    @Value("${CHAT_REST_URL_PREFIX}")
    private String CHAT_REST_URL_PREFIX;
    @Value("${ADMINISTRATION_REST_URL_PREFIX}")
    private String ADMINISTRATION_REST_URL_PREFIX;
    @Value("${SEARCH_REST_URL_PREFIX}")
    private String SEARCH_REST_URL_PREFIX;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @Autowired
    PageService pageService;
    @RequestMapping(value = "/issue")
    public ModelAndView issue(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Map params = RestUtil.getParameterMap(request);
        params.put("code","issueType");
        String issueTypeCode ="issueType";
        List<Type> types = pageService.getTypeListByPCode(issueTypeCode);
        modelAndView.addObject("types", types);
        modelAndView.setViewName("issue");
        return modelAndView;
    }
}
