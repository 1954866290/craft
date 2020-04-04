package com.zkwp.api.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @auther zhangkun
 * @date 2020/2/19 8:52
 **/
@Data
public class SystemImage implements Serializable {
    private static final long serialVersionUID = 3L;

    private int id;
    //文件路径
    private String filepath;

    //文件名
    private String imagename;

    //创建时间
    private String createtime;

    //删除标记
    private String delflag;


    public SystemImage(){

    }
}
