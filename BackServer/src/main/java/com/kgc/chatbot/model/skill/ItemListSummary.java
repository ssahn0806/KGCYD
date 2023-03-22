package com.kgc.chatbot.model.skill;

import org.json.simple.JSONObject;

public class ItemListSummary {
	
	private final JSONObject itemListSummary = new JSONObject();
	
	public ItemListSummary title(String title) {
		itemListSummary.put("title",title);
		return this;
	}
	
	public ItemListSummary description(String description) {
		itemListSummary.put("description",description);
		return this;
	}
	
	public JSONObject build() {
		return this.itemListSummary;
	}
}
