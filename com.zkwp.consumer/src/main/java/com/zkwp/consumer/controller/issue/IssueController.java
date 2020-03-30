package com.zkwp.consumer.controller.issue;

import com.alibaba.fastjson.JSONObject;
import com.sun.xml.internal.rngom.parse.host.Base;
import com.zkwp.api.bean.SystemType;
import com.zkwp.api.controller.BaseController;
import com.zkwp.api.utils.CommonListResult;
import com.zkwp.api.utils.CommonMapResult;
import com.zkwp.api.utils.CommonResult;
import com.zkwp.api.utils.RestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @auther zhangkun
 * @date 2020/2/22 12:06
 **/
@Controller
@RequestMapping(value = "/issue")
public class IssueController  {

    @Value("${SYSTEM_REST_URL_PREFIX}")
    private String SYSTEM_REST_URL_PREFIX;
    @Value("${ISSUE_REST_URL_PREFIX}")
    private String ISSUE_REST_URL_PREFIX;
    @Value("${CHAT_REST_URL_PREFIX}")
    private String CHAT_REST_URL_PREFIX;
    @Value("${ADMINISTRATION_REST_URL_PREFIX}")
    private String ADMINISTRATION_REST_URL_PREFIX;
    @Value("${SEARCH_REST_URL_PREFIX}")
    private String SEARCH_REST_URL_PREFIX;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/issueCenter")
    public ModelAndView issueCenter(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        MultiValueMap params = RestUtil.getParameterMap(request);
        params.set("code","issueType");
        CommonListResult commonListResult = restTemplate.postForObject(ISSUE_REST_URL_PREFIX+"/issueCenter/issueIndex/getTypes",params, CommonListResult.class);
        List<SystemType> types = (List<SystemType>) commonListResult.getData();
        modelAndView.addObject("types", types);
        modelAndView.setViewName("issueCenter");
        return modelAndView;
    }

    @RequestMapping(value = "/issueCenter/issue")
    @ResponseBody
    public String issue() {
        return "";
    }


    @RequestMapping(value = "/issueCenter/uploadImgFile")
    @ResponseBody
    public CommonMapResult uploadImgFile(@RequestParam("uploadImgFile") MultipartFile[] uploadImgFile, HttpServletRequest request) {
       MultiValueMap multiValueMap = new LinkedMultiValueMap<>();
       multiValueMap.put("uploadImgFile",uploadImgFile);
       String ipAddress = request.getRemoteAddr();
       multiValueMap.put("ipAddress",ipAddress);
       return restTemplate.postForObject(ISSUE_REST_URL_PREFIX+"/issueCenter/uploadImgFile",multiValueMap,CommonMapResult.class);
    }

    @RequestMapping(value = "/issueCenter/uploadFile")
    @ResponseBody
    public CommonResult uploadImageFile(HttpServletRequest request, HttpServletResponse response){
        MultiValueMap param = RestUtil.getParameterMap(request);
        return restTemplate.postForObject(ISSUE_REST_URL_PREFIX+"/issueCenter/uploadFile",param,CommonResult.class);
    }
}

