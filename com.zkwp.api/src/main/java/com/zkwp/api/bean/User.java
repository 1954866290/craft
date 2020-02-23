package com.zkwp.api.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @auther zhangkun
 * @date 2020/2/13 15:55
 **/
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String nickname;
    private String gender;
    private String introduction;
    private String region;
    private String state;
    private String headurl;
    private String create;
    private String update;

}
