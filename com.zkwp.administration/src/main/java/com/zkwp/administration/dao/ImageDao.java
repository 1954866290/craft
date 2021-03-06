package com.zkwp.administration.dao;

import com.zkwp.api.bean.SystemImage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @auther zhangkun
 * @date 2020/2/19 17:38
 **/
@Mapper
public interface ImageDao {

    int uploadImage(@Param("systemImage") SystemImage systemImage);

    List<SystemImage> getImages();
    
    List<SystemImage> getSwiperDataByImageName(String imageName);

    List<SystemImage> queryImages();

    
    int insertImage(@Param("Image")SystemImage Image);

    int deleteImage(@Param("id")Integer id);

    int checkNameExit(@Param("name") String name);

}
