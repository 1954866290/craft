package com.zkwp.api.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)// 过滤掉值为null的属性
public class Types {
	private Integer id;//主键id
	private String typeId;//类别id
	private String typeName;//类别名称
	private String typeOne;//小类别1
	private String typeTwo;//小类别2
	private String typeOneA;//小类别1细分
	private String typeOneB;//小类别1细分
	private String typeTwoA;//小类别2细分
	private String typeTwoB;//小类别2细分
	private String typeOneAIcon;//小类别1细分图标
	private String typeOneBIcon;//小类别1细分图标
	private String typeTwoAIcon;//小类别2细分图标
	private String typeTwoBIcon;//小类别2细分图标

}
