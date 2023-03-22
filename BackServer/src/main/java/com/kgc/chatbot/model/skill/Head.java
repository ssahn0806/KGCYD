package com.kgc.chatbot.model.skill;

import org.json.simple.JSONObject;

public class Head {
	private final JSONObject head = new JSONObject();
	
	public Head title(String title) {
		head.put("title",title);
		return this;
	}
	
	public JSONObject build() {
		return this.head;
	}
}
