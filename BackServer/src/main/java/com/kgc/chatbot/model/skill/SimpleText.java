package com.kgc.chatbot.model.skill;

import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public class SimpleText {
	private final JSONObject simpleText = new JSONObject();

	public SimpleText text(String text) {
		simpleText.put("text",text);
		return this;
	}
	public JSONObject build() {
		return this.simpleText;
	}
}
