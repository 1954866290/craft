package com.zkwp.api.bean;

import lombok.Data;

/**
 * @auther zhangkun
 * @date 2020/4/15 16:42
 **/
@Data
public class BizCommentAudit {
    private String id;
    private String commentId;
    private String userId;
    private String reason;
    private String status;
    private String createtime;
    private String delflag;
}
