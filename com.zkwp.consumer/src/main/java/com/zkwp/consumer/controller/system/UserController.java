package com.zkwp.consumer.controller.system;

import com.alibaba.fastjson.JSONObject;
import com.zkwp.api.bean.OutputObject;
import com.zkwp.api.bean.User;
import com.zkwp.api.utils.CommonResult;
import com.zkwp.api.utils.RestUtil;
import com.zkwp.api.utils.StringUtil;
import com.zkwp.consumer.service.system.SystemService;
import com.zkwp.consumer.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @auther zhangkun
 * @date 2020/2/16 11:10
 **/
@Controller
@RequestMapping(value = "/system")
public class UserController {
    @Value("${SYSTEM_REST_URL_PREFIX}")
    private  String SYSTEM_REST_URL_PREFIX ;
    @Value("${ISSUE_REST_URL_PREFIX}")
    private  String ISSUE_REST_URL_PREFIX ;
    @Value("${CHAT_REST_URL_PREFIX}")
    private  String CHAT_REST_URL_PREFIX ;
    @Value("${ADMINISTRATION_REST_URL_PREFIX}")
    private  String ADMINISTRATION_REST_URL_PREFIX ;
    @Value("${SEARCH_REST_URL_PREFIX}")
    private  String SEARCH_REST_URL_PREFIX ;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SystemService systemService;

    @Autowired
    private ImageUtil imageUtil;

    @Value("user.default.headurl")
    private String headUrl;

    @RequestMapping(value = "/user/add")
    public boolean addUser(User user){
        return restTemplate.postForObject(SYSTEM_REST_URL_PREFIX+"/user/addUser",user,Boolean.class);
    }


    @RequestMapping(value = "/User/updateUser")
    @ResponseBody
    public CommonResult updateUser(HttpSession session, HttpServletRequest request, @Param("avatar_file") MultipartFile file) throws  Exception{
        Map params = RestUtil.getParameterMap(request);
        if(file!=null){
            String path = imageUtil.uploadFile(file);
            params.put("headurl",path);
        }else{
            params.put("headurl","");
        }
        String province = StringUtil.objToString(params.get("province"));
        String city = StringUtil.objToString(params.get("city"));
        if(city.equals("请选择")){
            params.put("city","未知");
        }
        if(province.equals("请选择")){
            params.put("province",province);
        }
        String userid = StringUtil.objToString(session.getAttribute("userid"));
        params.put("userid",userid);
        return systemService.updateUser(params);
    }

}
