package com.zkwp.system.dao;

import com.zkwp.api.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @auther zhangkun
 * @date 2020/4/12 18:43
 **/
@Mapper
public interface IUserDao {
    User getUserById(@Param("userid")String userid);
}
