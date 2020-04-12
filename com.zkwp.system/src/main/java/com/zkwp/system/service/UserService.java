package com.zkwp.system.service;

import com.zkwp.api.utils.CommonResult;
import com.zkwp.system.dao.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther zhangkun
 * @date 2020/4/12 18:42
 **/
@Service
public class UserService {

    @Autowired
    IUserDao iUserDao;
    public CommonResult getUserById(String userid){
        return CommonResult.success(iUserDao.getUserById(userid));
    }
}
