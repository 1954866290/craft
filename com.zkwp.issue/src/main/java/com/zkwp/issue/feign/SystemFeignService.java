package com.zkwp.issue.feign;

import com.zkwp.api.bean.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @auther zhangkun
 * @date 2020/4/13 22:03
 **/
@FeignClient(value = "craft-system", path = "/craft/system")
@RequestMapping(value = "/feign-api")
public interface SystemFeignService {
    @PostMapping(value = "/User/getUserById")
    User getUserById(@RequestParam("userid") String userid);
}
