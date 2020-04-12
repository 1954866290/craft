package com.zkwp.system.service;

import com.zkwp.api.bean.User;
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
    public User getUserById(String userid){
        return iUserDao.getUserById(userid);
    }
}
