package com.zkwp.search.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkwp.api.bean.Types;
import com.zkwp.api.bean.UserPublic;
import com.zkwp.search.dao.SmallRoutineImageDao;
import com.zkwp.search.service.SmallRoutineImageService;
@Service
public class SmallRoutineImageServiceImpl implements SmallRoutineImageService{
	@Autowired
	private SmallRoutineImageDao smallRoutineImageDao;
	@Override
	public List<UserPublic> getSwiperDataByImageName(String imageName) {
		// TODO Auto-generated method stub
		return this.smallRoutineImageDao.getSwiperDataByImageName(imageName);
	}
	@Override
	public List<UserPublic> getImageName(String imageName) {
		// TODO Auto-generated method stub
		return this.smallRoutineImageDao.getImageName(imageName);
	}
	@Override
	public List<Types> getTypes() {
		// TODO Auto-generated method stub
		return this.smallRoutineImageDao.getTypes();
	}

}
