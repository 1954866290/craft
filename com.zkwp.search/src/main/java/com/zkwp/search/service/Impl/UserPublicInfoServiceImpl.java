package com.zkwp.search.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkwp.api.bean.Issue;
import com.zkwp.api.bean.User;
import com.zkwp.api.bean.UserPublic;
import com.zkwp.search.dao.UserPublicInfoDao;
import com.zkwp.search.service.UserPublicInfoService;
@Service
public class UserPublicInfoServiceImpl implements UserPublicInfoService{
	@Autowired
	private UserPublicInfoDao userPublicInfoDao;

	@Override
	public User getUserInfoById(String userid) {
		// TODO Auto-generated method stub
		return this.userPublicInfoDao.getUserInfoById(userid);
	}

	@Override
	public Issue getWorksInfoById(String userid) {
		// TODO Auto-generated method stub
		return this.userPublicInfoDao.getWorksInfoById(userid);
	}

	@Override
	public UserPublic getIssueInfoByWorksId(String worksid) {
		// TODO Auto-generated method stub
		return this.userPublicInfoDao.getIssueInfoByWorksId(worksid);
	}

	@Override
	public void updateUserPubInfoByWorksId(UserPublic userPub) {
		// TODO Auto-generated method stub
		this.userPublicInfoDao.updateUserPubInfoByWorksId(userPub);
	}

	@Override
	public void saveUserPubInfo(UserPublic userPub) {
		// TODO Auto-generated method stub
		this.userPublicInfoDao.saveUserPubInfo(userPub);
	}

	@Override
	public List<UserPublic> getGoodsListByTitle(String typeName) {
		// TODO Auto-generated method stub
		return this.userPublicInfoDao.getGoodsListByTitle(typeName);
	}

}
