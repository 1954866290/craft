package com.zkwp.issue.dao;

import com.zkwp.api.bean.SystemType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.hibernate.annotations.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @auther zhangkun
 * @date 2020/3/21 14:12
 **/
@Mapper
public interface IssueDao {


    List<SystemType> getTypes(@Param("code") String code);

    int insertTempImage(Map param);
}