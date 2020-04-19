package com.zkwp.wechat.issue.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zkwp.api.bean.Issue;
import com.zkwp.api.utils.CommonResult;
import com.zkwp.api.utils.StringUtil;
import com.zkwp.wechat.feign.IssueFeignService;
import com.zkwp.wechat.issue.controller.WechatIssueController;
import com.zkwp.wechat.util.ImageUtil;
@Service
public class WechatIssueService {
	 private Logger logger = LoggerFactory.getLogger(WechatIssueController.class);

	    @Autowired
	    IssueFeignService issueFeignService;

	    @Autowired
	    ImageUtil imageUtil;

	    private final String pathPre = "http://116.62.114.28:8080/";

	    public CommonResult videoIssue(MultipartFile video, Map params) {
	        String videoPath = imageUtil.uploadFile(video);
	        videoPath = pathPre + videoPath;
	        params.put("videoPath", videoPath);
	        return CommonResult.success(issueFeignService.doIssue(params));
	    }

	    public Issue getIssueByCode(Map params) {
	        String issuecode = StringUtil.objToString(params.get("issuecode"));
	        Issue issue = issueFeignService.getIssueByCode(issuecode);
	        return issue;
	    }

}
