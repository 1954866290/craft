package com.zkwp.administration.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zkwp.administration.service.TypeService;
import com.zkwp.api.bean.Type;
import com.zkwp.api.utils.CommonResult;
import com.zkwp.api.utils.RestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @auther zhangkun
 * @date 2020/3/29 20:41
 **/
@Controller
@RequestMapping(value = "/system")
public class TypeController {

    @Autowired
    TypeService typeService;


    @RequestMapping(value = "/type")
    public ModelAndView type(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        Map param = RestUtil.getParameterMap(request);
        PageHelper.startPage(pageNum,10);
        modelAndView.setViewName("type");
        List<Type> list = typeService.queryTypes(param);
        PageInfo<Type> pageInfo = new PageInfo<>(list);
        modelAndView.addObject("pageInfo",pageInfo);
        return  modelAndView;
    }

    @RequestMapping(value = "/type/queryTypes",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public CommonResult queryTypes(HttpServletRequest request){
        Map param = RestUtil.getParameterMap(request);
        Integer pageNum = (Integer) param.get("pageNum");
        if(pageNum==null){
            pageNum = 1;
        }
        PageHelper.startPage(pageNum,10);
        List<Type> list = typeService.queryTypes(param);
        PageInfo<Type> pageInfo = new PageInfo<>(list);
        return  CommonResult.success(pageInfo);
    }

    @RequestMapping(value = "/type/insertType",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public CommonResult insertType(HttpServletRequest request){
        Map param = RestUtil.getParameterMap(request);
        try {
            return  CommonResult.success(typeService.insertType(param)==1?"添加成功":"添加失败");
        }catch (Exception e){
            return CommonResult.failed("抱歉，请联系运维");
        }
    }

    @RequestMapping(value = "/type/deleteType",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public CommonResult deleteType(HttpServletRequest request){
        Map param = RestUtil.getParameterMap(request);
        try{
            return CommonResult.success(typeService.deleteType(param));
        }catch (Exception e){
            return CommonResult.failed("抱歉，请联系运维");
        }
    }

    @RequestMapping(value = "/type/updateType" ,method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public CommonResult updateType(HttpServletRequest request){
        Map param = RestUtil.getParameterMap(request);
        try{
            return CommonResult.success(typeService.updateType(param));
        }catch (Exception e){
            return CommonResult.failed("抱歉,请联系运维");
        }
    }
}
