package com.kgc.chatbot.model.skill;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class SmsResponseBuilder {
	private final String type = "SMS";
	private final String from = "01051163268";
//	private final String subject = "채널 친구관계 변동 알림";
	private final JSONArray messages = new JSONArray();
	private JSONObject response = new JSONObject();
	
	public SmsResponseBuilder() {
		response.put("type",type);
		response.put("from",from);
//		response.put("subject",subject);
		response.put("messages",messages);
	}
	
	public JSONObject addContent(String content) {
		response.put("content",content);
		return response;
	}
	
	public JSONObject addReceiver(String number) {
		JSONObject rec = new JSONObject();
		rec.put("to",number);
		messages.add(rec);
		return response;
	}
	public JSONObject build() {
		return response;
	}
}
