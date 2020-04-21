package com.zkwp.consumer.feign;

import com.zkwp.api.bean.Type;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @auther zhangkun
 * @date 2020/4/11 11:12
 **/

@FeignClient(value = "craft-administration" ,path = "/craft/administration")
@RequestMapping(value = "/feign-api")
public interface AdministrationFeignService {

    @PostMapping(value = "/Type/getTypeListByPCode")
    List<Type> getTypeListByPCode(@RequestParam("pcode")String pcode);

    @PostMapping(value = "/Type/getTypeByCode")
    Type getTypeByCode(@RequestParam("code")String code);
}
