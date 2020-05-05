package com.zkwp.search.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.zkwp.api.bean.Issue;
import com.zkwp.api.bean.OutputObject;
import com.zkwp.api.bean.SystemImage;
import com.zkwp.api.bean.Type;
import com.zkwp.api.bean.UserPublic;
import com.zkwp.api.bean.WechatSysFile;
import com.zkwp.search.service.SmallRoutineService;
import com.zkwp.search.service.TypesService;
import com.zkwp.search.service.UserPublicInfoService;

@Controller
public class SmallRoutineController {
	@Autowired
	private SmallRoutineService smallRoutineService;
	@Autowired
	private TypesService typesService;
	@Autowired
	private UserPublicInfoService userPublicInfoService;
	private static String BIG_SORT = "bigSort";
	private static String TYPES_FOR_USER = "ProductionType";
     /* 
     * 根据图片名称获取图片信息，主要用于获取小程序首页轮播图和导航栏图片信息
     */
    @RequestMapping(value = "/getImageDataByImageName", method = RequestMethod.GET)
    @ResponseBody
    public OutputObject getSwiperDataByName(String imageName, String pageSize) {
    	OutputObject out = new OutputObject();
    	List<WechatSysFile> beans = (List<WechatSysFile>) smallRoutineService.getSwiperDataByImageName(imageName);
    	out.setList(beans);
    	return out;
    }
    /*
     * 根据图片名称获取图片信息，主要用于获取小程序首页轮播图和导航栏图片信息
     */
    @RequestMapping(value = "/getBreakPageByName", method = RequestMethod.GET)
    @ResponseBody
    public OutputObject getBreakPageByName(String pageNumber, String pageSize) {
    	OutputObject out = new OutputObject();
    	List<Issue> total = smallRoutineService.getfloorData();
    	int pageNum = Integer.parseInt(pageNumber);
    	int pageSize1 = Integer.parseInt(pageSize);
    	PageHelper.startPage(pageNum, pageSize1);
    	List<Issue> beans = smallRoutineService.getfloorData();
    	System.out.println(beans);
    	out.setSearchTotals(total.size());
    	out.setReturnList(beans);
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
    		Type type = types.get(i);
			String code = type.getCode();
			List<Type> smallTypes = typesService.getTypeList(code);
			type.setList(smallTypes);
			resultList.add(type);
		}
    	out.setReturnList(resultList);
    	return out;
    }
    /**
     * 根据类别名称，获取商品列表信息
     */
    @RequestMapping(value="/getGoodsListInfo", method = RequestMethod.GET)
    @ResponseBody
    public OutputObject getGoodsListInfo(HttpServletRequest request) {
    	// 获取前端传入的请求参数
    	String typeName = request.getParameter("name");
    	int pageNum = Integer.parseInt(request.getParameter("pagenum"));
    	int pageSize = Integer.parseInt(request.getParameter("pagesize"));
    	OutputObject out = new OutputObject();
    	if ("推荐".equals(typeName)) {
    		List tuiJiaTotal = userPublicInfoService.getTuiJianList(typeName);
    		PageHelper.startPage(pageNum, pageSize);
    		List tuiJianList = userPublicInfoService.getTuiJianList(typeName);
    		out.setReturnList(tuiJianList);
    		out.setSearchTotals(tuiJiaTotal.size());
    		return out;
    	}
    	if ("新品".equals(typeName)) {
    		List newTotal = userPublicInfoService.getNewList(typeName);
    		PageHelper.startPage(pageNum, pageSize);
    		List newList = userPublicInfoService.getNewList(typeName);
    		out.setReturnList(newList);
    		out.setSearchTotals(newTotal.size());
    		return out;
    	}
    	// 先查询出总条数，再进行分页查询
    	List<WechatSysFile> total = userPublicInfoService.getGoodsListByTitle(typeName);
    	PageHelper.startPage(pageNum, pageSize);
    	List<WechatSysFile> goodsList = userPublicInfoService.getGoodsListByTitle(typeName);
    	out.setList(goodsList);
    	out.setSearchTotals(total.size());
    	return out;
    }
    /**
     * 用户上传图片
     */
    @RequestMapping(value="/uploadImages")
    @ResponseBody
    public OutputObject uploadImages(HttpServletRequest request, @RequestParam("imageTempSrc") MultipartFile file) {
    	System.out.println(request.getParameter("description"));
    	System.out.println(file.getOriginalFilename());
    	OutputObject out = new OutputObject();
        return out;
    }
    
    /**
     * 小程序根据作品id获取作品详情
     */
    @RequestMapping(value = "/getDetailsByGoodsId", method = RequestMethod.GET)
    @ResponseBody
    public String getDetailsByGoodsId(HttpServletRequest request) {
    	OutputObject out = new OutputObject();
    	String goodsId = request.getParameter("goods_id");
    	Issue worksInfo = smallRoutineService.getWorksInfo(goodsId);
    	List list = new ArrayList<>();
    	list.add(worksInfo.getOneimagepath());
    	list.add(worksInfo.getTwoimagepath());
    	list.add(worksInfo.getThreeimagepath());
    	list.add(worksInfo.getFourimagepath());
    	worksInfo.setImagesList(list);
        return JSON.toJSONString(worksInfo);
    }
    /**
     * 获取用户发布作品时需要选择的分类信息
     * @return
     */
    @RequestMapping(value = "/getTypesInfo", method = RequestMethod.GET)
    @ResponseBody
    public OutputObject getTypesInfo() {
    	OutputObject out = new OutputObject();
    	List<Type> types = typesService.getTypeList(TYPES_FOR_USER);
    	List<String> typeList = new ArrayList<String>();
    	for (Type type : types) {
			String sortName = type.getName();
			typeList.add(sortName);
		}
    	out.setReturnList(typeList);
    	return out;
    }
    
    /**
     * 根据关键字进行作品类别搜索
     */
    @RequestMapping(value = "/getSearchInfo", method = RequestMethod.GET)
    @ResponseBody
    public OutputObject getSearchInfo(HttpServletRequest request) {
    	OutputObject out = new OutputObject();
    	String searchKeyword = request.getParameter("query");
    	List<Type> types = typesService.searchInfo(searchKeyword);
    	out.setReturnList(types);
    	return out;
    }

}
