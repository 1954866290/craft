package com.zkwp.search.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zkwp.api.bean.Issue;
import com.zkwp.api.bean.User;
import com.zkwp.api.bean.UserPublic;
import com.zkwp.api.bean.WechatSysFile;
@Mapper
public interface UserPublicInfoDao {
	
	// 根据用户id得到用户信息
	User getUserInfoById(String userid);
	// 根据用户id得到作品信息
	Issue getWorksInfoById(String userid);
	// 根据作品id去查询userPub
	UserPublic getIssueInfoByWorksId(String worksid);
	// 更新userPub
    void updateUserPubInfoByWorksId(UserPublic userPub);
	// 插入userPub
	void saveUserPubInfo(UserPublic userPub);
	//根据类别名称查询商品列表
	List<WechatSysFile> getGoodsListByTitle(String typeName);
	// 获取推荐列表
	List getTuiJianList(String typeName);
	// 获取新品列表
    List getNewList(String typeName);

}
