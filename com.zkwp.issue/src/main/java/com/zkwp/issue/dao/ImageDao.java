package com.zkwp.issue.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @auther zhangkun
 * @date 2020/3/29 17:39
 **/
@Mapper
public interface ImageDao {


    int insertImageUploadRecord(Map param);
}
