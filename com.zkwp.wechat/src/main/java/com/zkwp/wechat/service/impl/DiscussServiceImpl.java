package com.zkwp.wechat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkwp.api.bean.BizComment;
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

}
