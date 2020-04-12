package com.zkwp.api.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * @auther zhangkun
 * @date 2020/3/29 21:02
 **/
@Data
@JsonInclude(value = Include.NON_NULL)
public class Type {
    private int id;
    private String name;
    private String code;
    private String description;
    private int seq;
    private int delflag;
    private String parentCode;
    private String createtime;
    private List list;
}
