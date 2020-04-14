package com.zkwp.consumer.controller;

import com.zkwp.api.bean.Issue;
import com.zkwp.api.bean.SystemType;
import com.zkwp.api.bean.Type;
import com.zkwp.api.bean.User;
import com.zkwp.api.utils.CommonListResult;
import com.zkwp.api.utils.RestUtil;
import com.zkwp.api.utils.StringUtil;
import com.zkwp.consumer.feign.AdministrationFeignService;
import com.zkwp.consumer.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    @Autowired
    PageService pageService;

    @Value("ProductionType")
    String ProductionType;

    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        Map params = RestUtil.getParameterMap(request);
        List<Type> types = pageService.getTypeListByPCode(ProductionType);
        String userid = StringUtil.objToString(session.getAttribute("userid"));
        User user = pageService.getUserById(userid);
        for(int i = 1; i<=types.size();i++){
            Type type = types.get(i-1);
            modelAndView.addObject("type"+i,type);
            List<Issue> issueList = pageService.getIssueListByTypeCode(type.getCode());
            modelAndView.addObject("issueList"+i,issueList);
        }
        modelAndView.addObject("user",user);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }


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
