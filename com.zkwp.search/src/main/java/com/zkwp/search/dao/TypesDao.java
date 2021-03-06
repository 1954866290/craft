package com.zkwp.search.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zkwp.api.bean.SystemImage;
import com.zkwp.api.bean.Type;
@Mapper
public interface TypesDao {
	List<Type> getTypeList(String bigSortName);
	SystemImage getImageSrcBySmallTypeName(String imageSrcName);
	List<SystemImage> getImageAll();
	// 根据用户输入关键字进行搜索
	List<Type> searchInfo(String searchKeyword);

}
