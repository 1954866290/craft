package com.zkwp.consumer.service.system;

import com.zkwp.api.bean.User;
import com.zkwp.consumer.feign.SystemFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
