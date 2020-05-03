package com.zkwp.wechat.feign;


import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zkwp.api.bean.OutputObject;

@FeignClient(value = "craft-system" ,path = "/craft/system")
@RequestMapping(value = "/feign-api")
public interface UserLoginFeign {
	
	@GetMapping("/wechat/sendLoginCode")
    OutputObject wechatUserLogin(@RequestParam("phone") String phone);
	
	@GetMapping("/wechat/checkRandomCode")
    OutputObject randomCodeCheck(@RequestParam("phone") String phone, @RequestParam("code") String code);

}
