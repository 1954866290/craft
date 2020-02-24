package com.zkwp.system.service;

import com.zkwp.api.bean.User;

public interface UserService {
	User getUserInfoByUsername(String username);
	
	User userRegister(User user);

}
