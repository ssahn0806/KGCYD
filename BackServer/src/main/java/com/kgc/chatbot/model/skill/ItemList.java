package com.kgc.chatbot.model.skill;

import org.json.simple.JSONObject;

public class ItemList {

	
	private final JSONObject itemList = new JSONObject();
	
	public ItemList title(String title) {
		itemList.put("title",title);
		return this;
	}
	
	public ItemList description(String description) {
		itemList.put("description",description);
		return this;
	}
	
	public JSONObject build() {
		return this.itemList;
	}
}
