package com.zkwp.issue.service;

import com.zkwp.api.bean.SystemType;
import com.zkwp.api.utils.ImageUtils;
import com.zkwp.api.utils.StringUtil;
import com.zkwp.issue.dao.IssueDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class IssueService {

    private Logger logger = LoggerFactory.getLogger(IssueService.class);

    @Autowired
    private IssueDao issueDao;



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
            String path = ImageUtils.uploadFile(fileBytes, ext, null);
            Map insertTempImageMap = new HashMap();
            insertTempImageMap.put("uuid", uuid);
            insertTempImageMap.put("ipAddress", ipAddress);
            insertTempImageMap.put("path", path);
            count+=issueDao.insertTempImage(insertTempImageMap);
        }
        Map resultMap =new HashMap();
        resultMap.put("uuid",uuid);
        resultMap.put("count",count);
        resultMap.put("ipAddress",ipAddress);
        return resultMap;
    }


    public int uploadFile(Map param) throws Exception{
        MultipartFile multipartFile = (MultipartFile) param.get("uploadFile");
        String ipAddress = StringUtil.objToString(param.get("ipAddress"));
        UUID uuid = UUID.randomUUID();
        byte[] fileBytes = multipartFile.getBytes();
        String originFileName = multipartFile.getOriginalFilename();
        String ext = originFileName.substring(originFileName.lastIndexOf("."));
        String path = ImageUtils.uploadFile(fileBytes,ext,null);
        Map insertTempImageMap = new HashMap();
        insertTempImageMap.put("uuid", uuid);
        insertTempImageMap.put("ipAddress", ipAddress);
        insertTempImageMap.put("path", path);
        return issueDao.insertTempImage(insertTempImageMap);
    }
}
