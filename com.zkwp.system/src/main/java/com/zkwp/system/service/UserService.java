package com.zkwp.system.service;

import com.zkwp.api.bean.User;
import com.zkwp.system.dao.UserDao;
import com.zkwp.system.util.StringUtil;
import com.zkwp.system.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @auther zhangkun
 * @date 2020/2/16 11:45
 **/
@Service
public class UserService {

    @Autowired
    private UserDao userDao;



    public Map getUserState(String username){
        return userDao.getUserState(username);
    }
}
