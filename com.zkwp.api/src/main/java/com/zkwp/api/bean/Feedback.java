package com.zkwp.api.bean;

import lombok.Data;

@Data
public class Feedback {
	private String id;
	private String userId;
	private String userQuestion;
	private String userImage;
	private String answer;

}
