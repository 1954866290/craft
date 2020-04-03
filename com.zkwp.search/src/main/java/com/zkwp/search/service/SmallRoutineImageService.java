package com.zkwp.search.service;

import java.util.List;

import com.zkwp.api.bean.Types;
import com.zkwp.api.bean.UserPublic;

public interface SmallRoutineImageService {
		List<UserPublic> getSwiperDataByImageName(String imageName);
		List<UserPublic> getImageName(String imageName);
		List<Types> getTypes();


}
