package com.zkwp.wechat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zkwp.api.bean.BizComment;
import com.zkwp.api.bean.Feedback;
import com.zkwp.api.bean.OutputObject;
import com.zkwp.api.bean.User;
@Mapper
public interface DiscussDao {
	
	int saveDiscussContent(BizComment bizComment);

	List<BizComment> getDiscussInfo(String worksId);

	String getUserId(String worksId);

	String getNickname(String userId);

	int saveFeedback(Feedback feedback);

	List<Feedback> getMyFeedbackInfo(String userId);

	int getViewCount(String worksId);

	int updateViewCount(String worksId, int finalViewCount);

	User getUserInfo(String phone);

	int updateUserInfo(User user);

}
