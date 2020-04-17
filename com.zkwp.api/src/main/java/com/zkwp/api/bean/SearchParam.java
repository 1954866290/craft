package com.zkwp.api.bean;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

/*
 * 搜索参数实体，可以根据包含的字段进行搜索
 */
@Data
@ToString
public class SearchParam implements Serializable{
	private String keyword;//关键字可以为username,title,description
	private String type;//分类
	private String price_min;// 价格区间
	private String price_max;

}
