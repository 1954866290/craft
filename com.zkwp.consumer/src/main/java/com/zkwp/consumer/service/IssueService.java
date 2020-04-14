package com.zkwp.consumer.service;

import com.zkwp.api.utils.CommonResult;
import com.zkwp.consumer.controller.issue.IssueController;
import com.zkwp.consumer.feign.IssueFeignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @auther zhangkun
 * @date 2020/4/13 22:18
 **/

@Service
public class IssueService {

    private Logger logger = LoggerFactory.getLogger(IssueController.class);

    @Autowired
    IssueFeignService issueFeignService;

    public CommonResult doIssue(Map params){
        MultipartFile video = (MultipartFile) params.get("video");
        MultipartFile cover = (MultipartFile) params.get("cover");
        return CommonResult.success(issueFeignService.doIssue(video,cover,params));
    }
}
