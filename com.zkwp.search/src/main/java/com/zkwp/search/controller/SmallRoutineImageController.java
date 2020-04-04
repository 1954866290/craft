package com.zkwp.search.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zkwp.api.bean.OutputObject;
import com.zkwp.api.bean.Types;
import com.zkwp.api.bean.UserPublic;
import com.zkwp.search.service.SmallRoutineImageService;

@Controller
public class SmallRoutineImageController {
	@Autowired
	private SmallRoutineImageService smallRoutineImageService;
	/*
     * 根据图片名称获取图片信息，主要用于获取小程序首页轮播图和导航栏图片信息
     */
    @RequestMapping(value = "/getImageDataByImageName", method = RequestMethod.GET)
    @ResponseBody
    public OutputObject getSwiperDataByName(String imageName) {
    	List<UserPublic> beans = (List<UserPublic>) smallRoutineImageService.getSwiperDataByImageName(imageName);
    	OutputObject out = new OutputObject();
    	out.setList(beans);
    	return out;
    }
}
