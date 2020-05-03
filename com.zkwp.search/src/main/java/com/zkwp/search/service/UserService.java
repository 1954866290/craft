package com.zkwp.search.service;

import java.util.List;

import com.zkwp.api.bean.Issue;
import com.zkwp.api.bean.User;

public interface UserService {
	User getUserIdByNickname(String wechatNickname);
	List<Issue> getUserWorksById(String userId);

}
