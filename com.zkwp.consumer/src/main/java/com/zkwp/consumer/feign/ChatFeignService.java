package com.zkwp.consumer.feign;

import com.zkwp.api.bean.BizComment;
import com.zkwp.api.utils.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @auther zhangkun
 * @date 2020/4/16 21:33
 **/

@FeignClient(value = "craft-chat" ,path = "/craft/chat")
@RequestMapping(value = "/feign-api")
public interface ChatFeignService {

    @PostMapping("/Comment/getCommentsTop5")
    List<Map> getCommentsTop5(@RequestBody Map params);

    @PostMapping("/Comment/findLikeRecond")
    int findLikeRecond(@RequestParam("userid")String userid,@RequestParam("issueid")String issueid);

    @PostMapping("/Comment/doComment")
    CommonResult doComment(@RequestBody Map params);

    @PostMapping("/Comment/getCommentsByUserId")
    List<Map> getCommentsByUserId(@RequestParam("userid")String userid);
}
