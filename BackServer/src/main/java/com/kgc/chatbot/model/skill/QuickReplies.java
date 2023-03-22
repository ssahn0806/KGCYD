package com.kgc.chatbot.model.skill;

import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public class QuickReplies {

	private final JSONObject quickReplies = new JSONObject();
	
	public QuickReplies label(String label) {
		quickReplies.put("label",label);
		return this;
	}
	
	public QuickReplies action(String action) {
		quickReplies.put("action",action);
		return this;
	}
	
	public QuickReplies messageText(String messageText) {
		quickReplies.put("messageText", messageText);
		return this;
	}
	
	public QuickReplies blockId(String blockId) {
		quickReplies.put("blockId",blockId);
		return this;
	}
	
	public QuickReplies extra(JSONObject extra) {
		quickReplies.put("extra",extra);
		return this;
	}
	
	public JSONObject build(){
		return this.quickReplies;
	}
}
