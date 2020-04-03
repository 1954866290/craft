package com.zkwp.api.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
/*
 * elasticSearch搜索实体类，包含user表和发布表的所有信息
 */

@Data
@JsonInclude(value = Include.NON_NULL)// 过滤掉值为null的属性
public class UserPublic {
	private String worksid;//作品id
    private String username;//用户名
    private String password;//密码
    private String phone;//手机号
    private String email;//邮箱
    private String nickname;//微博名称
    private String gender;//性别
    private String introduction;//个人简介
    private String region;//所在地区
    private String state;//状态
    private String headurl;//头像
    private String usercreatetime;//用户创建时间
    private String userupdatetime;//用户信息修改时间
    private String title;//发布作品标题
    private String discription;//发布作品描述
    private String viewcount;//观看次数
    private String charmingcount ;//点赞数
    private String type;//作品类型
    private String issueCreatedTime;//发布时间
    private String issueUpdatedTime;//修改时间
    private String delflag;//删除标志
    private String oneimagepath;//第一张图片路径
    private String twoimagepath;//第二张图片路径
    private String threeimagepath;//第三张图片路径
    private String fourimagepath;//第四张图片路径
    private String videopath;//视频路径
    private String price;//价格
    private String image_src;//图片路径
    private String goods_id;//图片id
    private String imagename;//图片名称
}
