package com.zkwp.search.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zkwp.api.bean.Issue;
import com.zkwp.api.bean.OutputObject;
import com.zkwp.api.bean.User;
import com.zkwp.api.utils.StringUtil;
import com.zkwp.search.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userSerivce;
	
	/**
	 * 根据用户微信昵称获取用户的发布信息
	 * @param nickname
	 * @return
	 */
	@RequestMapping(value = "/getUserInfoById", method = RequestMethod.GET)
	@ResponseBody
	public OutputObject getUserInfoById(HttpServletRequest request) {
		OutputObject out = new OutputObject();
		String nickname = request.getParameter("nickname");
		User user = userSerivce.getUserIdByNickname(nickname);
		if (StringUtil.isNotBlank(user.getPhone())) {
			String userId = user.getUserid();
			List<Issue> works = userSerivce.getUserWorksById(userId);
			out.setReturnList(works);
		}
		return out;
	}

}
