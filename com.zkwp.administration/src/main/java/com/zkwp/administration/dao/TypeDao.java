package com.zkwp.administration.dao;

import com.zkwp.api.bean.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @auther zhangkun
 * @date 2020/3/29 20:45
 **/
@Mapper
public interface TypeDao {

    int insertType(Map param);


    List<Type> queryTypes(Map param);

    int deleteType(Map param);

    int updateType(Map param);

}
