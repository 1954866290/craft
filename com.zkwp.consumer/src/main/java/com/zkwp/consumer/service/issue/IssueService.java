package com.zkwp.consumer.service.issue;

import com.zkwp.api.bean.Issue;
import com.zkwp.api.utils.CommonResult;
import com.zkwp.api.utils.StringUtil;
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

    public CommonResult doIssue(MultipartFile video ,MultipartFile cover,Map params){
        return CommonResult.success(issueFeignService.doIssue(video,cover,params));
    }

    public Issue getIssueById(Map params){
        String issueid = StringUtil.objToString(params.get("issueid"));
        Issue issue =  issueFeignService.getIssueById(issueid);
        return  issue;
    }
}
