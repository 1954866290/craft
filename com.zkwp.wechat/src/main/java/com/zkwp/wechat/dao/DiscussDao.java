package com.zkwp.wechat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zkwp.api.bean.BizComment;
@Mapper
public interface DiscussDao {
	
	int saveDiscussContent(BizComment bizComment);

	List<BizComment> getDiscussInfo(String worksId);

	String getUserId(String worksId);

	String getNickname(String userId);

}
