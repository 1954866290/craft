package com.zkwp.api.bean;

import lombok.Data;

/**
 * @auther zhangkun
 * @date 2020/3/29 21:02
 **/
@Data
public class Type {
    private int id;
    private String name;
    private String code;
    private String description;
    private int seq;
    private int delflag;
}
