package com.zkwp.search.service;

import java.util.List;

import com.zkwp.api.bean.Issue;
import com.zkwp.api.bean.UserPublic;
import com.zkwp.api.bean.WechatSysFile;

public interface SmallRoutineService {
	List<WechatSysFile> getSwiperDataByImageName(String imageName);
	Issue getWorksInfo(String goodsId);
	List<Issue> getfloorData();
}
