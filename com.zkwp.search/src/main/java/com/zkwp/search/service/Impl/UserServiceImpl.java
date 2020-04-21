package com.zkwp.search.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkwp.api.bean.Issue;
import com.zkwp.api.bean.User;
import com.zkwp.search.dao.UserDao;
import com.zkwp.search.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	@Override
	public User getUserIdByNickname(String wechatNickname) {
		// TODO Auto-generated method stub
		return this.userDao.getUserIdByNickname(wechatNickname);
	}

	@Override
	public List<Issue> getUserWorksById(String userId) {
		// TODO Auto-generated method stub
		return this.userDao.getUserWorksById(userId);
	}

}
