package com.zkwp.consumer.service.issue;

import com.zkwp.api.bean.Issue;
import com.zkwp.api.utils.CommonResult;
import com.zkwp.api.utils.StringUtil;
import com.zkwp.consumer.controller.issue.IssueController;
import com.zkwp.consumer.feign.IssueFeignService;
import com.zkwp.consumer.util.ImageUtil;
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

    @Autowired
    ImageUtil imageUtil;

    private final String pathPre = "http://116.62.114.28:8080/";

    public CommonResult doIssue(MultipartFile video, MultipartFile cover, Map params) {
        String coverPath = imageUtil.uploadFile(cover);
        coverPath = pathPre + coverPath;
        String videoPath = imageUtil.uploadFile(video);
        videoPath = pathPre + videoPath;
        params.put("coverPath", coverPath);
        params.put("videoPath", videoPath);
        return CommonResult.success(issueFeignService.doIssue(params));
    }

    public Issue getIssueByCode(Map params) {
        String issuecode = StringUtil.objToString(params.get("issuecode"));
        Issue issue = issueFeignService.getIssueByCode(issuecode);
        return issue;
    }
}
