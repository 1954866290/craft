package com.zkwp.wechat.service;

import java.util.List;


import com.zkwp.api.bean.BizComment;

public interface DiscussService {
	// 插入评论
	int saveDiscussContent(BizComment bizComment);
    // 查询评论
	List<BizComment> getDiscussInfo(String worksId);
	// 根据作品id获取用户id
	String getUserId(String worksId);
	// 根据用户id获取用户昵称
	String getNickname(String userId);

}
