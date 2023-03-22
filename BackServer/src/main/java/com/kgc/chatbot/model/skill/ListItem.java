package com.kgc.chatbot.model.skill;

import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public class ListItem {
	
	private final JSONObject listItem = new JSONObject();
	
	public ListItem title(String title) {
		listItem.put("title",title);
		return this;
	}
	
	public ListItem description(String description) {
		listItem.put("description",description);
		return this;
	}
	
	public ListItem imageUrl(String imageUrl) {
		listItem.put("imageUrl",imageUrl);
		return this;
	}
	
	public ListItem link(Link link) {
		listItem.put("link",link.build());
		return this;
	}
	
	public ListItem action(String action) {
		listItem.put("action",action);
		return this;
	}
	
	public ListItem blockId(String blockId) {
		listItem.put("blockId",blockId);
		return this;
	}
	
	public ListItem messageText(String messageText) {
		listItem.put("messageText",messageText);
		return this;
	}
	
	public ListItem extra(JSONObject extra) {
		listItem.put("extra",extra);
		return this;
	}
	
	public JSONObject build() {
		return this.listItem;
	}
}
