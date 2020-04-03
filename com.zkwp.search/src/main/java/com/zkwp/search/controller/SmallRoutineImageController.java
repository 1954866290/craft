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
    /*
     * 根据图片名称获取图片信息，主要用于获取小程序首页底部图片信息
     */
    @RequestMapping(value = "/getImageName", method = RequestMethod.GET)
    @ResponseBody
    public OutputObject getImageName(String imageName) {
    	OutputObject out = new OutputObject();
    	List<UserPublic> beans1 = (List<UserPublic>) smallRoutineImageService.getSwiperDataByImageName(imageName);
    	List<UserPublic> beans2 = (List<UserPublic>) smallRoutineImageService.getImageName(imageName);
    	JSONObject json1 = new JSONObject();
    	json1.put("name", "陶瓷器具");
    	JSONObject json2 = new JSONObject();
    	json2.put("floor_title", json1);
    	json2.put("product_list", beans1);
    	JSONObject json3 = new JSONObject();
    	json3.put("name", "中国文化");
    	JSONObject json4 = new JSONObject();
    	json4.put("floor_title", json3);
    	json4.put("product_list", beans2);
    	List<JSONObject> list = new ArrayList<JSONObject>();
    	list.add(json2);
    	list.add(json4);
    	out.setJsonList(list);
     	return out;
    }
    // 获取分类页面信息
    @RequestMapping(value="/getTypes", method = RequestMethod.GET)
    @ResponseBody
    public OutputObject getTypes() {
    	OutputObject out = new OutputObject();
    	List<Types> types = smallRoutineImageService.getTypes();
    	List<String> listMajor = new ArrayList<String>();//主要分类
    	List<String> listSecondary = new ArrayList<String>();//主要分类下的详细分类
    	for (Types type : types) {
    		listMajor.add(type.getTypeName());
    		listMajor.add(type.getTypeId());
    		listSecondary.add(type.getTypeOne());
    		listSecondary.add(type.getTypeTwo());
//    		listSecondary.add(type.getTypeThree());
//    		listSecondary.add(type.getTypeFour());
		}
    	//List<UserPublic> userPublic = smallRoutineImageService
    	return out;
    }

}
