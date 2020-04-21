package com.zkwp.search.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zkwp.api.bean.Issue;
import com.zkwp.api.bean.User;
@Mapper
public interface UserDao {
	// 根据微信昵称获取web端用户Id
	User getUserIdByNickname(String wechatNickname);
	// 根据用户id获取对应的所有发布的作品
	List<Issue> getUserWorksById(String userId);

}
