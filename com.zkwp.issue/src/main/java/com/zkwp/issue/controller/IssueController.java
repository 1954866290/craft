package com.zkwp.issue.controller;

import com.alibaba.fastjson.JSONObject;
import com.zkwp.api.bean.SystemType;
import com.zkwp.api.controller.BaseController;
import com.zkwp.api.utils.CommonListResult;
import com.zkwp.api.utils.CommonMapResult;
import com.zkwp.api.utils.CommonResult;
import com.zkwp.api.utils.RestUtil;
import com.zkwp.issue.service.IssueService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @auther zhangkun
 * @date 2020/2/22 9:37
 **/
@Controller
@RequestMapping("/issueCenter")
public class IssueController extends BaseController {

    @Autowired
    private IssueService issueService;


    @RequestMapping("/issueIndex")
    public ModelAndView issueCenter(Model model, @Param("username")String username){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("issueIndex");
        return modelAndView;
    }

    @RequestMapping(value = "/issueIndex/getTypes" ,method ={RequestMethod.POST})
    @ResponseBody
    public CommonListResult getTypes(@Param("code")String code){
        List<SystemType> lists = issueService.getTypes(code);
        return CommonListResult.success(lists);
    }


    @RequestMapping(value = "/issueIndex/uploadImgFile" ,method ={RequestMethod.POST})
    @ResponseBody
    public CommonMapResult uploadImgFile(HttpServletRequest request){
        MultiValueMap param = RestUtil.getParameterMap(request);
        try{
            return CommonMapResult.success(issueService.uploadImgFile(param));
        }catch (Exception e){
           return CommonMapResult.failed("上传失败");
        }
    }


    @RequestMapping(value = "/issueIndex/uploadFile",method = {RequestMethod.POST})
    @ResponseBody
    public CommonResult uploadFile(HttpServletRequest request){
        MultiValueMap param = RestUtil.getParameterMap(request);
        try{
            return CommonResult.success(issueService.uploadFile(param));
        }catch (Exception e){
            return  CommonResult.failed("上传失败");
        }
    }

    @RequestMapping(value = "/issueIndex/issueCraft",method = {RequestMethod.POST})
    @ResponseBody
    public CommonResult issueCraft(HttpServletRequest request){
        MultiValueMap param = RestUtil.getParameterMap(request);
        try{
            return CommonResult.success(issueService.issueCraft(param));
        }catch (Exception e){
            return CommonResult.failed("发布失败");
        }
    }
}
