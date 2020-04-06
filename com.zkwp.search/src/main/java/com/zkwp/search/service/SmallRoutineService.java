package com.zkwp.search.service;

import java.util.List;

import com.zkwp.api.bean.UserPublic;

public interface SmallRoutineService {
	List<UserPublic> getSwiperDataByImageName(String imageName);

}
