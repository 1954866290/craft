package com.zkwp.search.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkwp.api.bean.UserPublic;
import com.zkwp.search.dao.SmallRoutineDao;
import com.zkwp.search.service.SmallRoutineService;
@Service
public class SmallRoutineServiceImpl implements SmallRoutineService{
	@Autowired
	private SmallRoutineDao smallRoutineDao;
	@Override
	public List<UserPublic> getSwiperDataByImageName(String imageName) {
		// TODO Auto-generated method stub
		return this.smallRoutineDao.getSwiperDataByImageName(imageName);
	}

}
