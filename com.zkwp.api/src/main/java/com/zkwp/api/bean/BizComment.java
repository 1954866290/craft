package com.zkwp.api.bean;

import lombok.Data;

/**
 * @auther zhangkun
 * @date 2020/4/15 16:42
 **/
@Data
public class BizComment {
    private String id;
    private String issueId;
    private String userId;
    private String content;
    private String likeCount;
    private String status;
    private String commenttime;
    private String delflag;
    private String nickname;
}
