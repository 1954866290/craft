package com.zkwp.consumer.service.system;

import com.zkwp.api.utils.CommonResult;
import com.zkwp.api.utils.StringUtil;
import com.zkwp.consumer.feign.SystemFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @auther zhangkun
 * @date 2020/4/11 15:27
 **/
@Service
public class LoginService {


    @Autowired
    SystemFeignService systemFeignService;

    public CommonResult sendCode(Map params,String ip){
        if(!params.containsKey("type")){
            return CommonResult.failed();
        }
        String type = StringUtil.objToString(params.get("type"));
        String code;
        if(type.equals("phone")) {
            code = StringUtil.objToString(params.get("phone"));
        }else if(type.equals("email")){
            code = StringUtil.objToString(params.get("email"));
        }else {
            return CommonResult.failed();
        }
        return systemFeignService.sendCode(type,code,ip);
    }


    public CommonResult doLogin(Map params,String ip){
        if(!params.containsKey("type")){
            return CommonResult.failed();
        }
        String type = StringUtil.objToString(params.get("type"));
        String code;
        if(type.equals("phone")) {
            code = StringUtil.objToString(params.get("phone"));
        }else if(type.equals("email")){
            code = StringUtil.objToString(params.get("email"));
        }else {
            return CommonResult.failed();
        }
        String verify = StringUtil.objToString(params.get("verify"));
        return systemFeignService.doLogin(type,code,verify,ip);
    }
}
