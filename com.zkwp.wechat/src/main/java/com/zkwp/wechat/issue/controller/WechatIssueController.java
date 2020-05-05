package com.zkwp.wechat.issue.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.zkwp.api.bean.BizComment;
import com.zkwp.api.bean.Issue;
import com.zkwp.api.bean.OutputObject;
import com.zkwp.api.utils.StringUtil;
import com.zkwp.wechat.issue.service.WechatIssueService;
import com.zkwp.wechat.service.DiscussService;
/**
 * 微信小程序文件上传
 * @author 王朋
 *
 */
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
    WechatIssueService wechatIssueService;
    
    @Autowired
    private DiscussService discussService;

    /**
     * 视频发布
     * @param request
     * @param session
     * @param video
     * @return
     */
    @RequestMapping(value = "/videoIssue")
    @ResponseBody
    public OutputObject videoIssue(HttpServletRequest request, @Param("video")MultipartFile video) {
       OutputObject out = new OutputObject();
       String title = request.getParameter("issueTitle");
       String description = request.getParameter("description");
       String types = request.getParameter("types");
       String phone = request.getParameter("phone");
       String price = request.getParameter("price");
       Map<String, String> params = new HashMap<String, String>();
       params.put("title", title);
       params.put("description", description);
       params.put("type", types);
       String userId = wechatIssueService.getUserInfoByPhone(phone);
       params.put("userId", userId);
       params.put("price",price);
        try{
            out = wechatIssueService.videoIssue(video, params);
            }catch (Exception e){
            e.printStackTrace();
        }
        return out;
    }
    
    /**
     * 图片上传
     * 由于小程序上传多张图片时，会多次调用后台，因此该接口用户单纯的接收图片，并返回图片路径，小程序接收图片路径，
     * 并封装成数组，和作品相关信息再次调用 图片发布接口进行作品发布
     * @param imageSrc
     * @return
     */
    @RequestMapping(value = "/imagesUpload")
    @ResponseBody
    public OutputObject imagesUpload(@RequestParam("imageSrc") MultipartFile imageSrc) {
    	OutputObject out = new OutputObject();
    	out = wechatIssueService.imageUpload(imageSrc);
    	return out;
    }
    
    
    /**
     * 图片发布
     * @param request
     * @return
     */
    @RequestMapping(value = "/imagesIssue")
    @ResponseBody
    public OutputObject imagesIssue(HttpServletRequest request) {
    	OutputObject out = new OutputObject();
    	Map params = new HashMap();
    	String title = request.getParameter("issueTitle");
        String description = request.getParameter("description");
        String types = request.getParameter("types");
        String phone = request.getParameter("phone");
        String price = request.getParameter("price");
        String imagesPath = request.getParameter("imagesList");
        params.put("title", title);
        params.put("description", description);
        params.put("types", types);
        params.put("phone", phone);
        params.put("price", price);
        String userId = wechatIssueService.getUserInfoByPhone(phone);
        params.put("userId", userId);
        out = wechatIssueService.imagesIssue(imagesPath, params);
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
    	out = wechatIssueService.userLoginWechat(phone);
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
    	//String userInfo = request.getParameter("userInfo");
    	// Map<String, String> userMap = (Map<String, String>) JsonUtil.convertJson2Object(userInfo, Map.class);
    	String phone = request.getParameter("phone");
    	String code = request.getParameter("code");
    	out = wechatIssueService.checkRandomCode(phone, code);
    	return out;
    }
    
    /**
     * 根据用户手机号获取用户id和用户已发布的作品信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/getMyIssueInfo")
    @ResponseBody
    public OutputObject getMyIssueInfo(HttpServletRequest request) {
    	OutputObject out = new OutputObject();
    	OutputObject out1 = new OutputObject();
    	String requestPageNum = request.getParameter("pagenum");
    	String requestPageSize = request.getParameter("pagesize");
    	String phone = request.getParameter("phone");
    	// 根据手机号获取用户id
    	String userId = wechatIssueService.getUserInfoByPhone(phone);
    	// 根据用户id去查询用户发布作品信息
    	out = wechatIssueService.getWroksInfo(userId);
    	Map params = new HashMap();
    	params.put("pageNum", requestPageNum);
    	params.put("pageSize", requestPageSize);
    	params.put("userId", userId);
    	out1 = wechatIssueService.getWroksInfoBreakPage(params);
    	out1.setSearchTotals(out.getReturnList().size());
    	return out1;
    }
    
    /**
     * 根据作品id获取作品详细信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/getIssueDetails")
    @ResponseBody
    public OutputObject getIssueDetails(HttpServletRequest request) {
    	OutputObject out = new OutputObject();
    	String worksId = request.getParameter("id");
    	out = wechatIssueService.getWorksInfoById(worksId);
    	return out;
    }
    
    /**
     * 根据作品id删除作品
     * @param request
     * @return
     */
    @RequestMapping(value = "/deleteIssueById")
    @ResponseBody
    public OutputObject deleteIssueById(HttpServletRequest request) {
    	OutputObject out = new OutputObject();
    	String phone = request.getParameter("phone");
    	String worksId = request.getParameter("worksId");
    	// 根据手机号获取用户id
    	String userId = wechatIssueService.getUserInfoByPhone(phone);
    	out = wechatIssueService.deleteIssue(userId, worksId);
    	return out;
    }
    
    /**
     * 发表评论
     * @param request
     * @return
     */
    @RequestMapping(value = "/issueDiscuss")
    @ResponseBody
    public OutputObject issueDiscuss(HttpServletRequest request) {
    	OutputObject out = new OutputObject();
    	String issueId = request.getParameter("worksId");
    	String content = request.getParameter("content");
    	String userId= discussService.getUserId(issueId);
    	String nickname = discussService.getNickname(userId);
    	BizComment bizComment = new BizComment();
    	bizComment.setIssueId(issueId);
    	bizComment.setUserId(userId);
    	bizComment.setContent(content);
    	bizComment.setNickname(nickname);
    	bizComment.setCommenttime(StringUtil.dateToString(new Date()));
    	int i = discussService.saveDiscussContent(bizComment);
    	if (i==1) {
    		out.setReturnCode("0000");
    		out.setReturnMessage("评论发布成功");
    	}
    	return out;
    }
    
    /**
     * 获取作品评论内容
     * @param request
     * @return
     */
    @RequestMapping(value = "/getDiscussInfo")
    @ResponseBody
    public OutputObject getDiscussInfo(HttpServletRequest request) {
    	OutputObject out = new OutputObject();
    	String worksId = request.getParameter("worksId");
    	List<BizComment> discussListTotal = discussService.getDiscussInfo(worksId);
    	out.setReturnList(discussListTotal);
    	return out;
    }

}
