package com.zkwp.issue.controller;

import com.zkwp.api.utils.CommonResult;
import com.zkwp.issue.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

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
}
