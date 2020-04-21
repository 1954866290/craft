package com.zkwp.system.service;

import com.zkwp.api.bean.User;
import com.zkwp.api.utils.CommonResult;
import com.zkwp.api.utils.StringUtil;
import com.zkwp.system.dao.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

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

    public CommonResult updateUser(Map params){
        params.put("updatedtime", StringUtil.dateToString(new Date()));
        int i = iUserDao.updateUser(params);
        return i==1?CommonResult.success(i):CommonResult.failed();
    }
}
