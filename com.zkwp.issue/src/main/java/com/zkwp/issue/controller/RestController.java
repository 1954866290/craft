package com.zkwp.issue.controller;

import com.zkwp.api.bean.Issue;
import com.zkwp.api.utils.CommonResult;
import com.zkwp.issue.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public CommonResult doIssue(@RequestBody Map params){
        try{
            return CommonResult.success(issueService.doIssue(params));
        }catch (Exception e){
            return CommonResult.failed();
        }
    }

    @PostMapping("/issue/getIssueListByTypeCode")
    public List<Issue> getIssueListByTypeCode(@RequestParam("type") String type,@RequestParam("code") String code){
        try{
            return issueService.getIssueListByTypeCode(type,code);
        }catch (Exception e){
            return null;
        }
    }


    @PostMapping("/issue/getIssueByCode")
    public Issue getIssueByCode(@RequestParam("issuecode")String issuecode){
        try{
            return issueService.getIssueByCode(issuecode);
        }catch (Exception e){
            return null;
        }
    }

}
