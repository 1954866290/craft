package com.zkwp.administration.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zkwp.administration.dao.ImageDao;
import com.zkwp.api.bean.SystemImage;
import com.zkwp.api.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @auther zhangkun
 * @date 2020/2/19 17:21
 **/
@Service
public class ImageService {

    private Logger logger = LoggerFactory.getLogger(ImageService.class);

    @Autowired
    private ImageDao imageDao;

    public PageInfo<SystemImage> queryImages(Integer pageNumber, Integer pageSize){
        PageHelper.startPage(pageNumber,pageSize);
        List<SystemImage> Images = imageDao.queryImages();
        // 5表示设置连续显示的页号数目 1 2 3 4 5
        return new PageInfo<>(Images,5);
    }


    public int insertImage(SystemImage Image){
        return imageDao.insertImage(Image);
    }

    public int deleteImage(Integer id){
        return imageDao.deleteImage(id);
    }


    public Boolean checkNameExit(String name){
        if(imageDao.checkNameExit(name)==1) return true;
        else return false;
    }

    
    public int uploadImage(String path,String fileName) throws Exception{
        SystemImage systemImage = new SystemImage();
        systemImage.setCreatetime(StringUtil.dateToString(new Date()));
        systemImage.setFilepath(path);
        systemImage.setImagename(fileName);
        systemImage.setDelflag("0");
        logger.info("----------uploadFile path write SQL start ------------");
        int result = imageDao.uploadImage(systemImage);
        logger.info("----------uploadFile path write SQL ent ------------");
        return result;
    }

    public List<SystemImage> getImages(){
        return imageDao.getImages();
    }
    
    public List<SystemImage> getSwiperDataByImageName(String imageName) {
    	return imageDao.getSwiperDataByImageName(imageName);
    }
}
