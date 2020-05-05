package com.zkwp.search.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zkwp.api.bean.Issue;
import com.zkwp.api.bean.UserPublic;
import com.zkwp.api.bean.WechatSysFile;

@Mapper
public interface SmallRoutineDao {
	//获取小程序首页轮播图及导航栏信息
	List<WechatSysFile> getSwiperDataByImageName(String imageName);
    // 根据作品id获取作品详细信息
	Issue getWorksInfo(String goodsId);
	List<Issue> getFloorData();
}
