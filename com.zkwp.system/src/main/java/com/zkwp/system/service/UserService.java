package com.zkwp.system.service;

import com.zkwp.api.bean.User;

public interface UserService {
	User getUserInfoByPhone(String phone);
	
	User getUserInfoByEmail(String email);
	
	User userRegister(User user);
	
	User updateUserInfo(User user);

}
