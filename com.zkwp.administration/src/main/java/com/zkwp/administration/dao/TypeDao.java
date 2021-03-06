package com.zkwp.administration.dao;

import com.zkwp.api.bean.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @auther zhangkun
 * @date 2020/3/29 20:45
 **/
@Mapper
public interface TypeDao {
    List<Type> queryTypes(@Param("type")Type type);

    int insertType(@Param("type")Type type);


    Type getTypeById(@Param("id")Integer id);

    int deleteType(@Param("id")Integer id);

    int deleteTypes(@Param("ids")List<Integer> ids);

    int updateType(@Param("type")Type type);

    int checkNameExit(@Param("name") String name);

    List<Type> getTypeListByPCode(@Param("pcode")String pcode);

    Type getTypeByCode(@Param("code")String code);

}
