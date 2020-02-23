package com.zkwp.api.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @auther zhangkun
 * @date 2020/2/22 17:37
 **/
@Data
public class Issue implements Serializable {
    private static final long serialVersionUID = 4L;
    private String id;
    private String title;
    private String content;
    private String viewcount;
    private String charmingcount ;
    private String type;
    private String created;
    private String updated;
    private String delflag;


}
