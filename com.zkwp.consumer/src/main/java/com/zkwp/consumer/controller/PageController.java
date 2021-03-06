package com.zkwp.consumer.controller;

import com.zkwp.api.bean.*;
import com.zkwp.api.utils.RestUtil;
import com.zkwp.api.utils.StringUtil;
import com.zkwp.consumer.service.PageService;
import com.zkwp.consumer.service.chat.CommentService;
import com.zkwp.consumer.service.issue.IssueService;
import com.zkwp.consumer.service.system.HistoryService;
import com.zkwp.consumer.service.system.SystemService;
import org.bouncycastle.math.raw.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;

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

    ArrayBlockingQueue<BizSightHistory> historiesBlockingQueue = new ArrayBlockingQueue<BizSightHistory>(300);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    PageService pageService;

    @Autowired
    HistoryService historyService;

    @Autowired
    IssueService issueService;

    @Autowired
    CommentService commentService;

    @Autowired
    SystemService systemSevice;

    @Value("ProductionType")
    String ProductionType;

    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        Map params = RestUtil.getParameterMap(request);
        List<Type> types = pageService.getTypeListByPCode(ProductionType);
        String userid = StringUtil.objToString(session.getAttribute("userid"));
        User user = pageService.getUserById(userid);
        for (int i = 1; i <= types.size(); i++) {
            Type type = types.get(i - 1);
            modelAndView.addObject("type" + i, type);
            List<Issue> issueList = pageService.getIssueListByTypeCode("index",type.getCode());
            modelAndView.addObject("issueList" + i, issueList);
        }
        modelAndView.addObject("user", user);
        modelAndView.addObject("types", types);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping("/typeindex")
    public ModelAndView typeindex(HttpServletRequest request,HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        Map params = RestUtil.getParameterMap(request);
        List<Type> types = pageService.getTypeListByPCode(ProductionType);
        String code = StringUtil.objToString(params.get("type"));
        String userid = StringUtil.objToString(session.getAttribute("userid"));
        List<Issue> issueList = pageService.getIssueListByTypeCode("typeindex",code);
        Type type = pageService.getTypeByCode(code);
        modelAndView.addObject("issueList",issueList);
        modelAndView.addObject("type",type);
        modelAndView.addObject("types",types);
        modelAndView.setViewName("typeindex");
        return  modelAndView;
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }


    @RequestMapping(value = "/issue")
    public ModelAndView issue(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Map params = RestUtil.getParameterMap(request);
        List<Type> types = pageService.getTypeListByPCode(ProductionType);
        modelAndView.addObject("types", types);
        modelAndView.setViewName("issue");
        return modelAndView;
    }

    @RequestMapping(value = "/video")
    public ModelAndView video(HttpServletRequest request, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        Map params = RestUtil.getParameterMap(request);
        String userid = StringUtil.objToString(session.getAttribute("userid"));
        //historyService.addHistoryService(params);
        Issue issue = issueService.getIssueByCode(params);
        User user = systemSevice.getUserById(issue.getUserid());
        params.put("issueid",issue.getId());
        List<Map> comments = commentService.getCommentsTop5(params);
        int islike = commentService.findLikeRecond(userid, issue.getId());
        modelAndView.addObject("islike", islike);
        modelAndView.addObject("user", user);
        modelAndView.addObject("Maps", comments);
        modelAndView.addObject("issue", issue);
        List<Type> types = pageService.getTypeListByPCode(ProductionType);
        modelAndView.addObject("types", types);
        modelAndView.setViewName("video");
        return modelAndView;
    }

    @RequestMapping(value = "/issueSuccess")
    public ModelAndView issueSuccess(HttpServletRequest request, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("issueSuccess");
        List<Type> types = pageService.getTypeListByPCode(ProductionType);
        modelAndView.addObject("types", types);
        return modelAndView;
    }

    @RequestMapping(value = "/personnal")
    public ModelAndView personnal(HttpServletRequest request, HttpSession session) {
        String userid = StringUtil.objToString(session.getAttribute("userid"));
        User user = systemSevice.getUserById(userid);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        List<Type> types = pageService.getTypeListByPCode(ProductionType);
        modelAndView.addObject("types", types);
        // List<Map> commentList = commentService.getCommentsByUserId(userid);
        return modelAndView;
    }

    @RequestMapping(value = "introduction")
    public ModelAndView introduction(HttpServletRequest request, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
      /*  String userCount = systemSevice.getUserCount();
        modelAndView.addObject("userCount",userCount);
        String issueCount = systemSevice.getIssueCount();
        modelAndView.addObject("issueCount",issueCount);
        String scanCount = systemSevice.getScanCount();*/
        modelAndView.setViewName("introduction");
        List<Type> types = pageService.getTypeListByPCode(ProductionType);
        modelAndView.addObject("types", types);
        return modelAndView;
    }
}
