package com.zkwp.search.service;

import java.util.List;

import com.zkwp.api.bean.SystemImage;
import com.zkwp.api.bean.Type;

public interface TypesService {
	List<Type> getTypeList(String bigSortName);
	SystemImage getImageSrcBySmallTypeName(String imageSrcName);
	List<SystemImage> getImageAll();
}
