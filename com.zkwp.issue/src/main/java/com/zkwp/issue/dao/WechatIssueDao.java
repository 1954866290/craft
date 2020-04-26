package com.zkwp.issue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zkwp.api.bean.Issue;
@Mapper
public interface WechatIssueDao {
	// 保存上传视频信息
	int saveIssueRecord(Issue issue);
	// 根据手机号获取用户id
	String getUserIdByPhone(String phone);
	// 保存上传图片信息
	int saveImagesRecord(Issue issue);
	// 根据用户id获取用户已发布的作品
	List<Issue> getIssuesById(String userId);

}
