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
    private String worksid;
    private String title;
    private String discription;
    private String viewcount;
    private String charmingcount ;
    private String type;
    private String issueCreatedTime;
    private String issueUpdatedTime;
    private String delflag;
    private String oneimagepath;//第一张图片路径
    private String twoimagepath;//第二张图片路径
    private String threeimagepath;//第三张图片路径
    private String fourimagepath;//第四张图片路径
    private String videopath;//视频路径
    private String price;//价格


}
