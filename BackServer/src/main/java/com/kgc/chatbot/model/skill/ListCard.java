package com.kgc.chatbot.model.skill;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public class ListCard {
	
	private final JSONObject listCard = new JSONObject();
	private final JSONArray items = new JSONArray();
	private final JSONArray buttons = new JSONArray();
	public ListCard() {
		listCard.put("items",items);
		listCard.put("buttons",buttons);
	}
	public ListCard header(ListItem header) {
		listCard.put("header",header.build());
		return this;
	}
	
	public ListCard items(ListItem item) {
		items.add(item.build());
		return this;
	}
	
	public ListCard buttons(Button button) {
		buttons.add(button.build());
		return this;
	}
	
	public JSONObject build() {
		return this.listCard;
	}
}
