package com.kgc.chatbot.model.skill;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public class TextCard {
	private final JSONObject textCard = new JSONObject();
	private final JSONArray buttons = new JSONArray();
	
	public TextCard text(String text) {
		textCard.put("text",text);
		textCard.put("buttons", buttons);
		return this;
	}
	
	public TextCard button(Button button) {
		buttons.add(button);
		return this;
	}
	public JSONObject build() {
		return this.textCard;
	}

}
