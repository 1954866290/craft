package com.zkwp.system.dao;

import org.apache.ibatis.annotations.Mapper;

import com.zkwp.api.bean.User;

/**
 * @auther zhangkun
 * @date 2020/2/22 12:45
 **/
@Mapper
public interface UserDao {

     User getUserInfo(String username);// 根据邮箱获取用户信息
     
     User insertUserRegister(User user);

}
