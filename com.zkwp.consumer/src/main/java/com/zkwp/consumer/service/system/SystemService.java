package com.zkwp.consumer.service.system;

import com.zkwp.api.bean.User;
import com.zkwp.api.utils.CommonResult;
import com.zkwp.consumer.feign.SystemFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @auther zhangkun
 * @date 2020/4/17 21:56
 **/
@Service
public class SystemService {

    @Autowired
    SystemFeignService systemFeignService;
    public User getUserById(String userid){
       return systemFeignService.getUserById(userid);
    }

    public CommonResult updateUser(Map params){
        return systemFeignService.updateUser(params);
    }
}
