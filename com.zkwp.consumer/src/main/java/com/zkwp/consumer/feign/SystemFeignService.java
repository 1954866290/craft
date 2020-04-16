package com.zkwp.consumer.feign;

import com.zkwp.api.bean.BizSightHistory;
import com.zkwp.api.bean.User;
import com.zkwp.api.utils.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @auther zhangkun
 * @date 2020/4/11 15:28
 **/
@FeignClient(value = "craft-system" ,path = "/craft/system")
@RequestMapping(value = "/feign-api")
public interface SystemFeignService {

    @PostMapping(value = "/login/sendCode")
    CommonResult sendCode(@RequestParam("type")String type,@RequestParam("code")String code,@RequestParam("ip")String ip);

    @PostMapping(value = "/login/doLogin")
    CommonResult doLogin(@RequestParam("type")String type,@RequestParam("code")String code,@RequestParam("verify")String verify,@RequestParam("ip")String ip);

    @PostMapping(value = "/User/getUserById")
    User getUserById(@RequestParam("userid")String userid);

    @PostMapping(value = "/History/addHistory")
    CommonResult addHistoryService(@RequestParam("history")BizSightHistory bizSightHistory);
}
