package com.zkwp.api.bean;

import lombok.Data;

/**
 * @auther zhangkun
 * @date 2020/4/15 16:43
 **/
@Data
public class BizIssueAudit {

    private String id;
    private String issueId;
    private String userId;
    private String reason;
    private String status;
    private String createtime;
    private String delflag;
}
