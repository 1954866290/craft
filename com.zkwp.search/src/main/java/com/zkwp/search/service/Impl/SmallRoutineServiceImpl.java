package com.zkwp.search.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkwp.api.bean.Issue;
import com.zkwp.api.bean.UserPublic;
import com.zkwp.api.bean.WechatSysFile;
import com.zkwp.search.dao.SmallRoutineDao;
import com.zkwp.search.service.SmallRoutineService;
@Service
public class SmallRoutineServiceImpl implements SmallRoutineService{
	@Autowired
	private SmallRoutineDao smallRoutineDao;
	@Override
	public List<WechatSysFile> getSwiperDataByImageName(String imageName) {
		// TODO Auto-generated method stub
		return this.smallRoutineDao.getSwiperDataByImageName(imageName);
	}
	@Override
	public Issue getWorksInfo(String goodsId) {
		// TODO Auto-generated method stub
		return this.smallRoutineDao.getWorksInfo(goodsId);
	}
	@Override
	public List<Issue> getfloorData() {
		// TODO Auto-generated method stub
		return this.smallRoutineDao.getFloorData();
	}

}
