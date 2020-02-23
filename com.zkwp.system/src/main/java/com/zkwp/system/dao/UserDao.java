package com.zkwp.system.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @auther zhangkun
 * @date 2020/2/22 12:45
 **/
@Mapper
public interface UserDao {

     Map getUserState(@Param("username") String username);

}
