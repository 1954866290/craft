package com.zkwp.search.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.zkwp.api.bean.OutputObject;
import com.zkwp.api.bean.Type;
import com.zkwp.api.bean.UserPublic;
import com.zkwp.search.service.SmallRoutineService;
import com.zkwp.search.service.TypesService;

@Controller
public class SmallRoutineController {
	@Autowired
	private SmallRoutineService smallRoutineService;
	@Autowired
	private TypesService typesService;
	private static String BIG_SORT = "bigSort";
     /* 
     * 根据图片名称获取图片信息，主要用于获取小程序首页轮播图和导航栏图片信息
     */
    @RequestMapping(value = "/getImageDataByImageName", method = RequestMethod.GET)
    @ResponseBody
    public OutputObject getSwiperDataByName(String imageName, String pageSize) {
    	OutputObject out = new OutputObject();
    	List<UserPublic> beans = (List<UserPublic>) smallRoutineService.getSwiperDataByImageName(imageName);
    	out.setList(beans);
    	return out;
    }
    /*
     * 根据图片名称获取图片信息，主要用于获取小程序首页轮播图和导航栏图片信息
     */
    @RequestMapping(value = "/getBreakPageByName", method = RequestMethod.GET)
    @ResponseBody
    public OutputObject getBreakPageByName(String imageName, String pageNumber, String pageSize) {
    	OutputObject out = new OutputObject();
    	List<UserPublic> total = smallRoutineService.getSwiperDataByImageName(imageName);
    	int pageNum = Integer.parseInt(pageNumber);
    	int pageSize1 = Integer.parseInt(pageSize);
    	PageHelper.startPage(pageNum, pageSize1);
    	List<UserPublic> beans = smallRoutineService.getSwiperDataByImageName(imageName);
    	out.setSearchTotals(total.size());
    	out.setList(beans);
    	return out;
    }
    /**
     * 获取分类页面信息
     */
    @RequestMapping(value="/getSortPageInfo", method = RequestMethod.GET)
    @ResponseBody
    public OutputObject getSortPageInfo() {
    	OutputObject out = new OutputObject();
    	List resultList = new ArrayList();
    	List<Type> types = typesService.getTypeList(BIG_SORT);
    	for (int i = 0; i < types.size(); i++) {
			String code = types.get(i).getCode();
			String name = types.get(i).getName();
			int id = types.get(i).getId();
			List<Type> smallTypes = typesService.getTypeList(code);
			resultList.add(smallTypes);
		}
    	resultList.add(types);
    	out.setReturnList(resultList);
    	return out;
    }

}
