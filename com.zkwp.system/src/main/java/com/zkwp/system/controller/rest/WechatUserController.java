package com.zkwp.system.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zkwp.api.bean.OutputObject;
import com.zkwp.system.service.WechatLoginService;

@Controller
@RequestMapping(value ="/feign-api")
public class WechatUserController {
	
	@Autowired
	private WechatLoginService wechatLoginService;
	
	/**
	 * 发送短信验证码
	 */
	@RequestMapping(value = "/wechat/sendLoginCode", method = RequestMethod.GET)
	@ResponseBody
	public OutputObject sendLoginCode(String phone) {
		OutputObject out = new OutputObject();
		out = wechatLoginService.sendCodeWechat(phone);
		return out;
	}
	@RequestMapping(value = "/wechat/checkRandomCode", method = RequestMethod.GET)
	@ResponseBody
	public OutputObject checkRandomCode(String phone, String code) {
		OutputObject out = new OutputObject();
		out = wechatLoginService.checkCode(phone, code);
		return out;
	}
	
}
