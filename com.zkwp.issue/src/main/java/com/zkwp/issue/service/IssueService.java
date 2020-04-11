package com.zkwp.issue.service;

import com.zkwp.api.bean.SystemType;
import com.zkwp.api.utils.StringUtil;
import com.zkwp.issue.dao.IssueDao;
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

    @Autowired
    private IssueDao issueDao;

    @Autowired
    ImageUtil imageUtil;



   /* @Scheduled(cron = "")
    public void checkIssue(){

    }*/

    public List<SystemType> getTypes(String code) {
        return issueDao.getTypes(code);
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


    public Map uploadFile(Map param) throws Exception {
        MultipartFile multipartFile = (MultipartFile) param.get("uploadFile");
        String ipAddress = StringUtil.objToString(param.get("ipAddress"));
        UUID uuid = UUID.randomUUID();
        byte[] fileBytes = multipartFile.getBytes();
        String originFileName = multipartFile.getOriginalFilename();
        String ext = originFileName.substring(originFileName.lastIndexOf("."));
        String path = imageUtil.uploadFile(multipartFile);
        Map insertTempImageMap = new HashMap();
        insertTempImageMap.put("uuid", uuid);
        insertTempImageMap.put("ipAddress", ipAddress);
        insertTempImageMap.put("path", path);
        issueDao.insertTempImage(insertTempImageMap);
        Map<String, Object> result = new HashMap<>();
        result.put("uuid", uuid.toString());
        return result;
    }

    public Map issueCraft(Map param) throws Exception{
        param.put("createtime",StringUtil.dateToString(new Date()));
        param.put("updatetime",StringUtil.dateToString(new Date()));
        issueDao.issueCraft(param);
        return param;
    }
}
