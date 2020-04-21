package com.zkwp.system.dao;

import org.apache.ibatis.annotations.Mapper;

import com.zkwp.api.bean.User;
@Mapper
public interface WechatUserDao {
	// 创建新用户
	void createUserWechat(User user);
	// 根据手机号获取用户身份信息
	User getUserInfoByPhone(String phone);

}
