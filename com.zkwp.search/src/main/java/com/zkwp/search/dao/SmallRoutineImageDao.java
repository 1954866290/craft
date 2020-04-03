package com.zkwp.search.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zkwp.api.bean.Types;
import com.zkwp.api.bean.UserPublic;
@Mapper
public interface SmallRoutineImageDao {
	    //获取小程序首页轮播图及导航栏信息
		List<UserPublic> getSwiperDataByImageName(String imageName);
		//获取小程序首页底部页面信息
		List<UserPublic> getImageName(String imageName);
		//获取小程序分类页面类别信息
		List<Types> getTypes();

}
