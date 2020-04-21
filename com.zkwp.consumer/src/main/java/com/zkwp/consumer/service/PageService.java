package com.zkwp.consumer.service;

import com.zkwp.api.bean.Issue;
import com.zkwp.api.bean.Type;
import com.zkwp.api.bean.User;
import com.zkwp.consumer.feign.AdministrationFeignService;
import com.zkwp.consumer.feign.IssueFeignService;
import com.zkwp.consumer.feign.SystemFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther zhangkun
 * @date 2020/4/11 11:17
 **/
@Service
public class PageService {

    @Autowired
    AdministrationFeignService administrationFeignService;

    @Autowired
    SystemFeignService systemFeignService;

    @Autowired
    IssueFeignService issueFeignService;

    public List<Type> getTypeListByPCode(String pcode) {
        return administrationFeignService.getTypeListByPCode(pcode);
    }

    public User getUserById(String userid) {
        return systemFeignService.getUserById(userid);
    }

    public List<Issue> getIssueListByTypeCode(String type,String code){
        return issueFeignService.getIssueListByTypeCode(type,code);
    }

    public Type getTypeByCode(String code){
        return  administrationFeignService.getTypeByCode(code);
    }
}
