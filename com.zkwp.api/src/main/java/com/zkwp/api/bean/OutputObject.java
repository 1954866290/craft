package com.zkwp.api.bean;
/*
 * 统一出参类型
 */
public class OutputObject {
	private String returnCode;
	private String returnMessage;
	
	
	public OutputObject() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OutputObject(String returnCode, String returnMessage) {
		this.returnCode = returnCode;
		this.returnMessage = returnMessage;
	}
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
	
	
	

}
