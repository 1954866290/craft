package com.zkwp.api.bean;

import java.util.Map;

/*
 * 统一出参类型
 */
public class OutputObject {
	private String returnCode;
	private String returnMessage;
	private Map<String, String> beans;
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getReturnMessage() {
		return returnMessage;
	}
	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}
	public Map<String, String> getBeans() {
		return beans;
	}
	public void setBeans(Map<String, String> beans) {
		this.beans = beans;
	}
	public OutputObject(String returnCode, String returnMessage, Map<String, String> beans) {
		super();
		this.returnCode = returnCode;
		this.returnMessage = returnMessage;
		this.beans = beans;
	}
	public OutputObject() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
