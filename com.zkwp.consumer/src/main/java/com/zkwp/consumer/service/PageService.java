package com.zkwp.consumer.service;

import com.zkwp.api.bean.Type;
import com.zkwp.consumer.feign.AdministrationFeignService;
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

    public List<Type> getTypeListByPCode(String pcode) {
        return administrationFeignService.getTypeListByPCode(pcode);
    }

}
