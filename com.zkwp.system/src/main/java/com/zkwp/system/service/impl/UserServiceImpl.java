package com.zkwp.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkwp.api.bean.User;
import com.zkwp.system.dao.UserDao;
import com.zkwp.system.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;

	@Override
	public User getUserInfoByPhone(String phone) {
		// TODO Auto-generated method stub
		return this.userDao.getUserInfoByPhone(phone);
	}

	@Override
	public User userRegister(User user) {
		// TODO Auto-generated method stub
		return this.userDao.insertUserRegister(user);
	}

	@Override
	public User getUserInfoByEmail(String email) {
		// TODO Auto-generated method stub
		return this.userDao.getUserInfoByEmail(email);
	}
	

}
