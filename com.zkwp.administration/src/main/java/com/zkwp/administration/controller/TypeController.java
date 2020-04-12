package com.zkwp.administration.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zkwp.administration.service.TypeService;
import com.zkwp.api.bean.Type;
import com.zkwp.api.utils.CommonResult;
import com.zkwp.api.utils.RestUtil;
import org.apache.ibatis.annotations.Param;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
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


    @RequestMapping(value = "/type",method ={RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
    public String type(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                       Model model) {
        PageInfo<Type> pageInfo = typeService.queryTypes(pageNumber, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        return "type";
    }


    @RequestMapping(value = "/type/queryTypes", method ={RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
    @ResponseBody
    public CommonResult queryTypes(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                   Model model) {
            PageInfo<Type> pageInfo = typeService.queryTypes(pageNumber,pageSize);
            Map result = new HashMap();
            result.put("pageInfo",pageInfo);
            return CommonResult.success(result);
    }

    @RequestMapping(value =  "/type/insertType",method ={RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
    @ResponseBody
    public CommonResult insertType(Type type) {
        int res = typeService.insertType(type);
        if (res > 0)
            return CommonResult.success(res);
        else
            return CommonResult.failed();
    }

    @RequestMapping(value =  "/type/checkNameExit",method ={RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
    @ResponseBody
    public CommonResult checkNameExit(@Param("name") String name){
        boolean isExit = typeService.checkNameExit(name);
        if(isExit)
            return CommonResult.failed();
        else
            return CommonResult.success(isExit);
    }

    @RequestMapping(value =  "/type/getType/{id}",method ={RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
    @ResponseBody
    public CommonResult getType(@PathVariable("id") Integer id) {
        Type type = typeService.getTypeById(id);
        if (type != null)
            return CommonResult.success(type);
        else
            return CommonResult.failed();
    }

    @RequestMapping(value =  "/type/deleteType/{id}",method ={RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
    @ResponseBody
    public CommonResult deleteType(@PathVariable("id") Integer id) {
        int res = typeService.deleteType(id);
        if (res > 0)
            return CommonResult.success(res);
        else
            return CommonResult.failed();
    }

    @RequestMapping(value =  "/type/deleteTypes/{ids}",method ={RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
    @ResponseBody
    public CommonResult deleteTypes(@PathVariable("ids") String ids) {
        int res = typeService.deleteTypes(ids);
        if (res > 0)
            return CommonResult.success(res);
        else
            return CommonResult.failed();
    }


    @RequestMapping(value =  "/type/updateType/{id}",method ={RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
    @ResponseBody
    public CommonResult updateType(Type type) {
        int res = typeService.updateType(type);
        if (res > 0)
            return CommonResult.success(res);
        else
            return CommonResult.failed();
    }
}
