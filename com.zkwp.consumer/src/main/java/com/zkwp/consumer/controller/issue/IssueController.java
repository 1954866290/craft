package com.zkwp.consumer.controller.issue;

import com.zkwp.api.utils.CommonResult;
import com.zkwp.api.utils.RestUtil;
import com.zkwp.api.utils.StringUtil;
import com.zkwp.consumer.service.issue.IssueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @auther zhangkun
 * @date 2020/2/22 12:06
 **/
@Controller
@RequestMapping(value = "/issue")
public class IssueController  {

    private Logger logger = LoggerFactory.getLogger(IssueController.class);

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
    IssueService issueService;

    @RequestMapping(value = "/doIssue")
    @ResponseBody
    public CommonResult doIssue(StandardMultipartHttpServletRequest request1,HttpServletRequest request, HttpSession session) {
        Map params  = RestUtil.getParameterMap(request);
        Map<String,MultipartFile> multipartFileMap = request1.getFileMap();
        MultipartFile cover = multipartFileMap.get("cover");
        MultipartFile video = multipartFileMap.get("video");
        String userid = StringUtil.objToString(session.getAttribute("userid"));
        params.put("userid",userid);
        try{
            return CommonResult.success(issueService.doIssue(video,cover,params));
        }catch (Exception e){
            logger.error("IssueController::doIssue throws exception",e);
            return CommonResult.failed();
        }
    }

}

