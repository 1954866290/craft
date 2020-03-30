package com.zkwp.administration.service;

import com.zkwp.administration.dao.TypeDao;
import com.zkwp.api.bean.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @auther zhangkun
 * @date 2020/3/29 20:43
 **/
@Service
public class TypeService {

    @Autowired
    TypeDao typeDao;

    public List<Type> queryTypes(Map param){
        return typeDao.queryTypes(param);
    }

    public int insertType(Map param){
        return typeDao.insertType(param);
    }

    public int deleteType(Map param){
        return typeDao.deleteType(param);
    }

    public int updateType(Map param){
        return typeDao.updateType(param);
    }
}
