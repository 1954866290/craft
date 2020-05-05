package com.zkwp.wechat.service;

import java.util.List;


import com.zkwp.api.bean.BizComment;
import com.zkwp.api.bean.Feedback;
import com.zkwp.api.bean.OutputObject;

public interface DiscussService {
	// 插入评论
	int saveDiscussContent(BizComment bizComment);
    // 查询评论
	List<BizComment> getDiscussInfo(String worksId);
	// 根据作品id获取用户id
	String getUserId(String worksId);
	// 根据用户id获取用户昵称
	String getNickname(String userId);
	// 意见反馈
	int saveFeedback(Feedback feedback);
	// 获取我的意见反馈信息
	List<Feedback> getMyFeedbackInfo(String userId);
	// 获取作品的浏览次数或播放次数
	int getViewCount(String worksId);
	// 更新作品的浏览次数或播放次数
	int updateViewCount(String worksId, int finalViewCount);

}
