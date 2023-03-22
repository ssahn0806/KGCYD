package com.kgc.chatbot.model.skill;

import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public class Button {

	private final JSONObject button = new JSONObject();
	//action : webLink, message,phone,block,share,ooperator
	//messageText X -> button Label 
	
	public Button label(String label) {
		button.put("label",label);
		return this;
	}
	
	public Button action(String action) {
		button.put("action",action);
		return this;
	}
	
	public Button webLinkUrl(String webLinkUrl) {
		button.put("webLinkUrl",webLinkUrl);
		return this;
	}
	
	public Button messageText(String messageText) {
		button.put("messageText",messageText);
		return this;
	}
	
	public Button phoneNumber(String phoneNumber) {
		button.put("phoneNumber",phoneNumber);
		return this;
	}
	
	public Button blockId(String blockId) {
		button.put("blockId",blockId);
		return this;
	}
	
	public Button extra(JSONObject extra) {
		button.put("extra",extra);
		return this;
	}
	
	public JSONObject build(){
		return this.button;
	}
	
}
