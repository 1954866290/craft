package com.zkwp.api.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @auther zhangkun
 * @date 2020/2/13 15:56
 **/
@Data
public class Permission  implements Serializable {
    private static final long serialVersionUID = 2L;
    private String parentId;
    private String name;
    private String enname;
    private String url;
    private String description;
    private String created;
    private String updated;

}
