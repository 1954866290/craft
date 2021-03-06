package com.zkwp.issue.service;

import com.zkwp.api.bean.Issue;
import com.zkwp.api.bean.SystemType;
import com.zkwp.api.utils.CommonResult;
import com.zkwp.api.utils.StringUtil;
import com.zkwp.issue.dao.IssueDao;
import com.zkwp.issue.feign.SystemFeignService;
import com.zkwp.issue.util.ImageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
@Transactional
public class IssueService {

    private Logger logger = LoggerFactory.getLogger(IssueService.class);

    private final String pathPre = "http://116.62.114.28:8080/";
    @Autowired
    private IssueDao issueDao;

    @Autowired
    ImageUtil imageUtil;

    @Autowired
    SystemFeignService systemFeignService;


    public CommonResult uploadImages(MultipartFile[] multipartFiles, Map params) {
        List<String> pathList = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            String path = imageUtil.uploadFile(multipartFile);
            path = pathPre + path;
            //* 做插入 biz_issue

            pathList.add(path);

        }
        return CommonResult.success(1);
    }


    public CommonResult doIssue(Map params) {
        String videoPath = StringUtil.objToString(params.get("videoPath"));
        System.out.println(videoPath);
        String coverPath = StringUtil.objToString(params.get("coverPath"));
        String userid = StringUtil.objToString(params.get("userid"));
        Issue issue = new Issue();
        issue.setCreatedtime(StringUtil.dateToString(new Date()));
        issue.setOneimagepath(coverPath);
        issue.setTitle(StringUtil.objToString(params.get("title")));
        issue.setDescription(StringUtil.objToString(params.get("description")));
        issue.setUserid(userid);
        issue.setDelflag("0");
        issue.setCharmingcount("0");
        issue.setPrice(StringUtil.objToString(params.get("price")));
        issue.setType(StringUtil.objToString(params.get("type")));
        issue.setViewcount("0");
        issue.setVideopath(videoPath);
        issue.setUpdatedtime(StringUtil.dateToString(new Date()));
        issue.setCode(creatCode());
        int i = issueDao.insertIssueRecord(issue);
        return CommonResult.success(i);
    }
    private String creatCode(){
        //生成唯一id
        String code= UUID.randomUUID().toString();
        //替换uuid中的"-"
        code=code.replace("-", "");
        return code;
    }
    public List<Issue> getIssueListByTypeCode(String type,String code){
        return issueDao.getIssueListByTypeCode(type,code);
    }

    public Issue getIssueByCode(String issuecode){
        return  issueDao.getIssueByCode(issuecode);
    }

    public Map uploadImgFile(Map param) throws Exception {
        MultipartFile[] uploadImgFiles = (MultipartFile[]) param.get("uploadImgFile");
        String ipAddress = StringUtil.objToString(param.get("ipAddress"));
        UUID uuid = UUID.randomUUID();
        int count = 0;
        for (MultipartFile uploadImgFile : uploadImgFiles) {
            byte[] fileBytes = uploadImgFile.getBytes();
            String originFileName = uploadImgFile.getOriginalFilename();
            String ext = originFileName.substring(originFileName.lastIndexOf("."));
            String path = imageUtil.uploadFile(uploadImgFile);
            Map insertTempImageMap = new HashMap();
            insertTempImageMap.put("uuid", uuid);
            insertTempImageMap.put("ipAddress", ipAddress);
            insertTempImageMap.put("path", path);
            count += issueDao.insertTempImage(insertTempImageMap);
        }
        Map resultMap = new HashMap();
        resultMap.put("uuid", uuid);
        resultMap.put("count", count);
        resultMap.put("ipAddress", ipAddress);
        return resultMap;
    }


}
