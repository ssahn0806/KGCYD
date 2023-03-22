package com.kgc.chatbot.model.dto;

import lombok.Data;

@Data
public class Board {
	private int articleNo;
	private String userId;
	private String subject;
	private String content;
	private String thumbnail;
	private String registTime;
	private boolean isEvent;
	private String startEvent;
	private String endEvent;
}
