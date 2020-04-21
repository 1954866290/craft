package com.zkwp.wechat.issue.service;

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
	    IssueFeignService issueFeignService;
	    
	    @Autowired
	    UserLoginFeign userLoginFeign;

	    @Autowired
	    ImageUtil imageUtil;

	    private final String pathPre = "http://116.62.114.28:8080/";

	    public OutputObject videoIssue(MultipartFile video, Map params) {
	        String videoPath = imageUtil.uploadFile(video);
	        videoPath = pathPre + videoPath;
	        params.put("videoPath", videoPath);
	        OutputObject out = issueFeignService.doIssue(params);
	        return out;
	    }
        
	    public OutputObject userLoginWechat(String phone) {
	    	OutputObject out = new OutputObject();
			out = userLoginFeign.wechatUserLogin(phone);
			return out;
		}
	    
	    public OutputObject checkRandomCode(String phone, String code) {
	    	OutputObject out = new OutputObject();
	    	out = userLoginFeign.randomCodeCheck(phone, code);
	    	return out;
	    }
}
