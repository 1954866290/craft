package com.zkwp.consumer.feign;

import com.zkwp.api.bean.BizComment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @auther zhangkun
 * @date 2020/4/16 21:33
 **/

@FeignClient(value = "craft-chat" ,path = "/craft/chat")
@RequestMapping(value = "/feign-api")
public interface ChatFeignService {

    @PostMapping("/chat/getCommentsTop5")
    List<BizComment> getCommentsTop5(@RequestParam("issueid")String issueid);
}
