package com.zkwp.wechat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkwp.api.bean.BizComment;
import com.zkwp.api.bean.Feedback;
import com.zkwp.api.bean.OutputObject;
import com.zkwp.wechat.dao.DiscussDao;
import com.zkwp.wechat.service.DiscussService;
@Service
public class DiscussServiceImpl implements DiscussService{
	
	@Autowired
	private DiscussDao discussDao;

	@Override
	public int saveDiscussContent(BizComment bizComment) {
		// TODO Auto-generated method stub
		return this.discussDao.saveDiscussContent(bizComment);
	}

	@Override
	public List<BizComment> getDiscussInfo(String worksId) {
		// TODO Auto-generated method stub
		return this.discussDao.getDiscussInfo(worksId);
	}

	@Override
	public String getUserId(String worksId) {
		// TODO Auto-generated method stub
		return this.discussDao.getUserId(worksId);
	}

	@Override
	public String getNickname(String userId) {
		// TODO Auto-generated method stub
		return this.discussDao.getNickname(userId);
	}

	@Override
	public int saveFeedback(Feedback feedback) {
		// TODO Auto-generated method stub
		return this.discussDao.saveFeedback(feedback);
	}

	@Override
	public List<Feedback> getMyFeedbackInfo(String userId) {
		// TODO Auto-generated method stub
		return this.discussDao.getMyFeedbackInfo(userId);
	}

	@Override
	public int getViewCount(String worksId) {
		// TODO Auto-generated method stub
		return this.discussDao.getViewCount(worksId);
	}

	@Override
	public int updateViewCount(String worksId, int finalViewCount) {
		// TODO Auto-generated method stub
		return this.discussDao.updateViewCount(worksId, finalViewCount);
	}

}
