package com.zkwp.wechat.issue.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.zkwp.api.utils.CommonResult;
import com.zkwp.api.utils.RestUtil;
import com.zkwp.api.utils.StringUtil;
import com.zkwp.wechat.issue.service.WechatIssueService;
@Controller
public class WechatIssueController {
	private Logger logger = LoggerFactory.getLogger(WechatIssueController.class);

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
    WechatIssueService issueService;

    @RequestMapping(value = "/videoIssue")
    @ResponseBody
    public CommonResult videoIssue(HttpServletRequest request, HttpSession session,  @Param("video")MultipartFile video) {
        Map params  = RestUtil.getParameterMap(request);
//        String userid = StringUtil.objToString(session.getAttribute("userid"));
//        params.put("userid",userid);
//        String title = StringUtil.objToString(params.get("title"));
        try{
            CommonResult commonResult = new CommonResult();
            //commonResult.setMessage(title);
            return commonResult.success(issueService.videoIssue(video,params));
        }catch (Exception e){
            logger.error("IssueController::doIssue throws exception",e);
            return CommonResult.failed();
        }
    }


}
