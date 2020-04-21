package com.zkwp.wechat.issue.controller;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.zkwp.api.bean.OutputObject;
import com.zkwp.wechat.issue.service.WechatIssueService;
import com.zkwp.wechat.util.JsonUtil;
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
    
    /**
     * 视频发布
     * @param request
     * @param session
     * @param video
     * @return
     */
    @RequestMapping(value = "/videoIssue")
    @ResponseBody
    public OutputObject videoIssue(HttpServletRequest request, HttpSession session,  @Param("video")MultipartFile video) {
       OutputObject out = new OutputObject();
       String title = request.getParameter("issueTitle");
       String description = request.getParameter("description");
       String types = request.getParameter("types");
       Map<String, String> params = new HashMap<String, String>();
       params.put("title", title);
       params.put("description", description);
       params.put("type", types);
//        String userid = StringUtil.objToString(session.getAttribute("userid"));
        params.put("userid","12");
        params.put("coverPath","1111");
        params.put("price","100");
//        String title = StringUtil.objToString(params.get("title"));
        try{
            out = issueService.videoIssue(video, params);
        }catch (Exception e){
            logger.error("IssueController::doIssue throws exception",e);
        }
        return out;
    }
    
    /**
     * 获取短信验证码
     * @param request
     * @return
     */
    @RequestMapping(value = "/weChatSendCode", method = RequestMethod.GET)
    @ResponseBody
    public OutputObject UserLogin(HttpServletRequest request) {
    	OutputObject out = new OutputObject();
    	String phone = request.getParameter("phone");
    	out = issueService.userLoginWechat(phone);
    	return out;
    }
    /**
     * 校验短信验证码及用户身份信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/weChatCheckCode", method = RequestMethod.GET)
    @ResponseBody
    public OutputObject weChatCheckCode(HttpServletRequest request) {
    	OutputObject out = new OutputObject();
    	String userInfo = request.getParameter("userInfo");
    	Map<String, String> userMap = (Map<String, String>) JsonUtil.convertJson2Object(userInfo, Map.class);
    	String phone = userMap.get("phone");
    	String code = userMap.get("code");
    	out = issueService.checkRandomCode(phone, code);
    	return out;
    }


}
