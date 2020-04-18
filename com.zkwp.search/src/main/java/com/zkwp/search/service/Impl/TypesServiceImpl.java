package com.zkwp.search.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkwp.api.bean.SystemImage;
import com.zkwp.api.bean.Type;
import com.zkwp.api.bean.UserPublic;
import com.zkwp.search.dao.TypesDao;
import com.zkwp.search.service.TypesService;
@Service
public class TypesServiceImpl implements TypesService{
	@Autowired
	private TypesDao typesDao;

	@Override
	public List<Type> getTypeList(String bigSortName) {
		// TODO Auto-generated method stub
		return this.typesDao.getTypeList(bigSortName);
	}

	@Override
	public SystemImage getImageSrcBySmallTypeName(String imageSrcName) {
		// TODO Auto-generated method stub
		return this.typesDao.getImageSrcBySmallTypeName(imageSrcName);
	}

	@Override
	public List<SystemImage> getImageAll() {
		// TODO Auto-generated method stub
		return this.typesDao.getImageAll();
	}

	@Override
	public List<Type> searchInfo(String searchKeyword) {
		// TODO Auto-generated method stub
		return this.typesDao.searchInfo(searchKeyword);
	}

}
