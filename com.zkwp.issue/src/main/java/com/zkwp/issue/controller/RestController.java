package com.zkwp.issue.controller;

import com.zkwp.api.bean.Issue;
import com.zkwp.api.utils.CommonResult;
import com.zkwp.issue.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/feign-api")
public class RestController {

    @Autowired
    IssueService issueService;

    @PostMapping("/issue/uploadImages")
    public CommonResult uploadImages(MultipartFile[] multipartFiles, Map params) {
        try {
            return CommonResult.success(issueService.uploadImages(multipartFiles, params));
        } catch (Exception e) {
            return CommonResult.failed();
        }
    }

    @PostMapping("/issue/doIssue")
    public CommonResult doIssue(@RequestParam("video")MultipartFile video,@RequestParam("cover")MultipartFile cover, Map params){
        try{
            return CommonResult.success(issueService.doIssue(video,cover,params));
        }catch (Exception e){
            return CommonResult.failed();
        }
    }

    @PostMapping("/issue/getIssueListByTypeCode")
    public List<Issue> getIssueListByTypeCode(@RequestParam("code") String code){
        try{
            return issueService.getIssueListByTypeCode(code);
        }catch (Exception e){
            return null;
        }
    }


    @PostMapping("/issue/getIssueById")
    public Issue getIssueById(@RequestParam("issueid")String issueId){
        try{
            return issueService.getIssueById(issueId);
        }catch (Exception e){
            return null;
        }
    }

}
