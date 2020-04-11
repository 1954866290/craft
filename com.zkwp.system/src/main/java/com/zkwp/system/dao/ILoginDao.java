package com.zkwp.system.dao;

import com.zkwp.api.bean.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Mapper;

/**
 * @auther zhangkun
 * @date 2020/4/11 16:03
 **/
@Mapper
public interface ILoginDao {

    User getUserByEmail(@Param("email")String email);

    User getUserByPhone(@Param("phone")String phone);

    int createUser(@Param("User") User user);

}
