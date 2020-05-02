package com.zkwp.issue.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zkwp.api.bean.Issue;
import com.zkwp.api.bean.OutputObject;
import com.zkwp.issue.service.WechatIssueService;

@Controller
@RequestMapping(value ="/feign-api")
public class WechatVideoIssueController {
	
	@Autowired
	WechatIssueService wechatIssueService;
	
	
	/**
	 * 视频上传
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/wechat/videoUpload")
	@ResponseBody
	public OutputObject videoUpload(@RequestBody Map params) {
		OutputObject out = new OutputObject();
		out = wechatIssueService.uploadVideo(params);
		return out;
	}
	
	/**
	 * 图片上传
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/wechat/imagesUpload")
	@ResponseBody
	public OutputObject imagesUpload(@RequestParam("imagesPath") String imagesPath, @RequestBody Map params) {
		OutputObject out = new OutputObject();
		out = wechatIssueService.uploadPics(imagesPath, params);
		return out;
	}
	
	/**
	 * 根据手机号获取用户id
	 * @param phone
	 * @return
	 */
	@RequestMapping(value = "/wechat/getUserId")
	@ResponseBody
	public String getUserId(String phone) {
		OutputObject out = new OutputObject();
		String userId = wechatIssueService.getUserIdByPhone(phone);
		return userId;
	}
	
	/**
	 * 根据用户id获取发布作品总数
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/wechat/getIssueInfo")
	@ResponseBody
	public OutputObject getIssueInfo(@RequestParam("userId") String userId) {
		OutputObject out = new OutputObject();
		out = wechatIssueService.getIssuesByUserId(userId);
		return out;
	}
	
	/**
	 * 根据用户id获取发布作品
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/wechat/getIssueInfoAll")
	@ResponseBody
	public OutputObject getIssueInfoAll(@RequestBody Map params) {
		OutputObject out = new OutputObject();
		out = wechatIssueService.getIssuesByUserIdAll(params);
		return out;
	}
	
	@RequestMapping(value = "/wechat/getWorksInfoById")
	@ResponseBody
	public OutputObject getWorksInfoById(@RequestParam("worksId") String worksId) {
		OutputObject out = new OutputObject();
		out = wechatIssueService.getWorksInfoById(worksId);
		return out;
	}
	
	/**
	 * 删除作品
	 * @param userId
	 * @param worksId
	 * @return
	 */
	@RequestMapping(value = "/wechat/deleteWorksById")
	@ResponseBody
	public OutputObject deleteWorksById(@RequestParam("userId") String userId, @RequestParam("worksId") String worksId) {
		OutputObject out = new OutputObject();
		out = wechatIssueService.deleteIssue(userId, worksId);
		return out;
	}
}
