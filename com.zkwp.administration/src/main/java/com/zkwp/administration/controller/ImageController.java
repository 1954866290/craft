package com.zkwp.administration.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zkwp.administration.util.ImageUtil;
import com.zkwp.api.utils.CommonResult;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSON;
import com.zkwp.administration.service.ImageService;
import com.zkwp.api.bean.SystemImage;



/**
 * @auther zhangkun
 * @date 2020/2/19 17:06
 **/

@Controller
@RequestMapping("/system")
public class ImageController {

    private Logger logger = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    private ImageService imageService;

    @Autowired
    private ImageUtil imageUtil;



    @RequestMapping(value = "/image")
    public String Image(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                       Model model) {
        PageInfo<SystemImage> pageInfo = imageService.queryImages(pageNumber, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        return "Image";
    }


    @RequestMapping(value = "/image/queryImages", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public CommonResult queryImages(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                   Model model) {
        PageInfo<SystemImage> pageInfo = imageService.queryImages(pageNumber,pageSize);
        Map result = new HashMap();
        result.put("pageInfo",pageInfo);
        return CommonResult.success(result);
    }

    @RequestMapping(value =  "/image/insertImage",method ={RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
    @ResponseBody
    public CommonResult insertImage(SystemImage image) {
        int res = imageService.insertImage(image);
        if (res > 0)
            return CommonResult.success(res);
        else
            return CommonResult.failed();
    }

    @RequestMapping(value =  "/image/checkNameExit",method ={RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
    @ResponseBody
    public CommonResult checkNameExit(@Param("name") String name){
        boolean isExit = imageService.checkNameExit(name);
        if(isExit)
            return CommonResult.failed();
        else
            return CommonResult.success(isExit);
    }

    @RequestMapping(value = "/image/deleteImage/{id}",method ={RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
    @ResponseBody
    public CommonResult deleteImage(@PathVariable("id") Integer id) {
        int res = imageService.deleteImage(id);
        if (res > 0)
            return CommonResult.success(res);
        else
            return CommonResult.failed();
    }




    @GetMapping("/manager")
    public String systemImageManager(Model model){
       List<SystemImage> imageList = imageService.getImages();
       model.addAttribute("imageList",imageList);
       return "SystemImageManager";
    }

    @RequestMapping(value = "/uploadImage",method = {RequestMethod.GET,RequestMethod.POST})
    public String uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("filename") String fileName) throws Exception {
        byte[] fileBytes = file.getBytes();
        String originFileName = file.getOriginalFilename();
        String ext = originFileName.substring(originFileName.lastIndexOf("."));
        System.out.println(originFileName);
        logger.info("-------------图片上传开始--------------");
        String path =imageUtil.uploadFile(file);
        //ImageUtils.uploadFile(fileBytes, ext, null);
        logger.info("-------------图片上传结束--------path:"+path);
        path = "http://116.62.114.28:8080/"+path;
        int i = imageService.uploadImage(path, fileName);
        return i == 1 ? "上传成功" : "上传失败";
    }
    
    /*
     * 根据图片名称获取图片信息，主要用于获取轮播图信息
     */
    @RequestMapping(value = "/getImageDataByImageName", method = RequestMethod.GET)
    @ResponseBody
    public String getSwiperDataByName(String imageName) {
    	SystemImage image;
    	String returnJson = "";
    	StringBuilder sb = new StringBuilder();
    	Map<String, String> returnMap = new HashMap<String, String>();
    	List<SystemImage> beans = (List<SystemImage>) imageService.getSwiperDataByImageName(imageName);
    	returnJson = JSON.toJSONString(beans);
    	return returnJson;
    }
}
