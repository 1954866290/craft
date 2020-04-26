package com.zkwp.wechat.issue.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zkwp.api.bean.Issue;
import com.zkwp.api.bean.OutputObject;
import com.zkwp.api.utils.CommonResult;
import com.zkwp.api.utils.StringUtil;
import com.zkwp.wechat.feign.IssueFeignService;
import com.zkwp.wechat.feign.UserLoginFeign;
import com.zkwp.wechat.issue.controller.WechatIssueController;
import com.zkwp.wechat.util.ImageUtil;
@Service
public class WechatIssueService {
	 private Logger logger = LoggerFactory.getLogger(WechatIssueController.class);
	    
	    @Autowired
	    UserLoginFeign userLoginFeign;

	    @Autowired
	    ImageUtil imageUtil;
	    
	    @Autowired
	    IssueFeignService issueFeignService;

	    private final String pathPre = "http://116.62.114.28:8080/";
        
	    /**
	     * 视频上传
	     * @param video
	     * @param params
	     * @return
	     */
	    public OutputObject videoIssue(MultipartFile video, Map params) {
	    	OutputObject out = new OutputObject();
	    	String videoPath = imageUtil.uploadFile(video);
	        videoPath = pathPre + videoPath;
	        params.put("videoPath", videoPath);
	        out = issueFeignService.videoIssue(params);
	        return out;
	    }
	    
	    /**
	     * 单张图片上传
	     * @param imagePath
	     * @return
	     */
	    public OutputObject imageUpload(MultipartFile imagePath) {
	    	OutputObject out = new OutputObject();
	    	String returnImagePath = imageUtil.uploadFile(imagePath);
	    	returnImagePath = pathPre + returnImagePath;
	    	out.setReturnMessage(returnImagePath);
	    	return out;
	    }
	    
	    /**
	     * 图片发布
	     * @param imagesPath
	     * @param params
	     * @return
	     */
	    public OutputObject imagesIssue(String imagesPath, Map params) {
	    	OutputObject out = new OutputObject();
	        out = issueFeignService.imageIssue(imagesPath, params);
	        return out;
	    }
	    /**
	     * 根据手机号获取用户id
	     * @param phone
	     * @return
	     */
	    public String getUserInfoByPhone(String phone) {
	    	String userId = issueFeignService.getUserIdByPhone(phone);
	    	return userId;
	    }
        
	    /**
	     * 用户登录，获取短信验证码
	     * @param phone
	     * @return
	     */
	    public OutputObject userLoginWechat(String phone) {
	    	OutputObject out = new OutputObject();
			out = userLoginFeign.wechatUserLogin(phone);
			return out;
		}
	    
	    /**
	     * 验证码校验
	     * @param phone
	     * @param code
	     * @return
	     */
	    public OutputObject checkRandomCode(String phone, String code) {
	    	OutputObject out = new OutputObject();
	    	out = userLoginFeign.randomCodeCheck(phone, code);
	    	return out;
	    }
	    
	    /**
	     * 根据用户id获取用户发布的作品总数
	     * @param userId
	     * @return
	     */
	    public OutputObject getWroksInfo(String userId) {
	    	OutputObject out = new OutputObject(); 
	    	out = issueFeignService.getUserIssueInfo(userId);
	    	return out;
	    }
	    
	    /**
	     * 根据用户id获取用户发布的作品
	     * @param userId
	     * @return
	     */
	    public OutputObject getWroksInfoBreakPage(Map params) {
	    	OutputObject out = new OutputObject(); 
	    	out = issueFeignService.getUserIssueInfoAll(params);
	    	return out;
	    }


}
