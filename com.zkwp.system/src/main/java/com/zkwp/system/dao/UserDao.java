package com.zkwp.system.dao;

import org.apache.ibatis.annotations.Mapper;

import com.zkwp.api.bean.User;

/**
 * @auther zhangkun
 * @date 2020/2/22 12:45
 **/
@Mapper
public interface UserDao {
	
	 User getUserInfoByPhone(String phone);// 根据手机号获取用户信息

     User getUserInfoByEmail(String email);// 根据邮箱获取用户信息
     
     User insertUserRegister(User user);// 用户注册
     
     User updateUserInfo(User user);//用户信息修改

}
