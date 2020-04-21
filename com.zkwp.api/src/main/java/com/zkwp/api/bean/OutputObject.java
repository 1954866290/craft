package com.zkwp.api.bean;

import java.util.List;
import java.util.Map;

import lombok.Data;

/*
 * 统一出参类型
 */
@Data
public class OutputObject {
	private String returnCode;
	private String returnMessage;
	private Map<String, String> bean;
	private long searchTotals;// 搜索时需用到，表示搜索出来的结果数
	private List<UserPublic> list;// 搜索返回前端的结果
	private Map returnMap;
	private List returnList;
	private List<Map> finalList;
	
	
}
