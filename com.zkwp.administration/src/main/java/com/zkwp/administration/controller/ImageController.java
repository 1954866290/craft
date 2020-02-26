package com.zkwp.administration.controller;

import com.alibaba.fastjson.JSONObject;
import com.zkwp.administration.service.ImageService;
import com.zkwp.api.bean.SystemImage;
import com.zkwp.api.utils.ImageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * @auther zhangkun
 * @date 2020/2/19 17:06
 **/

@Controller
@RequestMapping("/system/image")
public class ImageController {

    private Logger logger = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    private ImageService imageService;

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
        logger.info("-------------图片上传开始--------------");
        String path = ImageUtils.uploadFile(fileBytes, ext, null);
        logger.info("-------------图片上传结束--------path:"+path);
        int i = imageService.uploadImage(path, fileName);
        return i == 1 ? "上传成功" : "上传失败";
    }
    
    /*
     * 根据图片名称获取图片信息，主要用于获取轮播图信息
     */
    @RequestMapping(value = "/getSwiperData", method = RequestMethod.GET)
    @ResponseBody
    public String getSwiperDataByName(String imageName) {
    	SystemImage image = imageService.getSwiperDataByImageName(imageName);
    	String swiperData = image.getFilepath();
    	// JSONObject datas = JSONObject.parseObject(swiperData);
    	return swiperData;
    }
}
