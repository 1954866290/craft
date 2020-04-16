package com.zkwp.api.bean;

import lombok.Data;

/**
 * @auther zhangkun
 * @date 2020/4/15 16:43
 **/
@Data
public class BizSightHistory {
    private String id;
    private String issueId;
    private String userId;
    private String createtime;
    private String delflag;
}
