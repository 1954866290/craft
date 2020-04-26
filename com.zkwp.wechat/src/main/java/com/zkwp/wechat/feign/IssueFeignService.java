package com.zkwp.wechat.feign;

import com.zkwp.api.bean.Issue;
import com.zkwp.api.bean.OutputObject;
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
 * @date 2020/4/13 22:19
 **/
@FeignClient(value = "craft-issue" ,path = "/craft/issue")
@RequestMapping(value = "/feign-api")
public interface IssueFeignService {
	@RequestMapping("/wechat/getUserId")
    String getUserIdByPhone(@RequestParam("phone") String phone);
	
	@RequestMapping("/wechat/videoUpload")
    OutputObject videoIssue(@RequestBody Map params);
	
	@RequestMapping("/wechat/imagesUpload")
    OutputObject imageIssue(@RequestParam("imagesPath") String imagesPath, @RequestBody Map params);
	
	@RequestMapping("/wechat/getIssueInfo")
	OutputObject getUserIssueInfo(@RequestParam("userId") String userId);
	
	@RequestMapping("/wechat/getIssueInfoAll")
	OutputObject getUserIssueInfoAll(@RequestBody Map params);
}
