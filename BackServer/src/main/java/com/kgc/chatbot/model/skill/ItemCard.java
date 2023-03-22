package com.kgc.chatbot.model.skill;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ItemCard {

	private final JSONObject itemCard = new JSONObject();
	private final JSONArray itemList = new JSONArray();
	private final JSONArray buttons = new JSONArray();
	public ItemCard() {
		itemCard.put("itemList",itemList);
		itemCard.put("buttons",buttons);
	}
	public ItemCard thumbnail(Thumbnail thumbnail) {
		itemCard.put("thumbnail",thumbnail.build());
		return this;
	}
	
	public ItemCard head(Head head) {
		itemCard.put("head",head.build());
		return this;
	}
	
	public ItemCard profile(Profile profile) {
		itemCard.put("profile",profile.build());
		return this;
	}
	
	public ItemCard imageTitle(ImageTitle imageTitle) {
		itemCard.put("imageTitle",imageTitle.build());
		return this;
	}
	
	public ItemCard itemList(ItemList itemList) {
		this.itemList.add(itemList.build());
		return this;
	}
	
	public ItemCard itemListAlignment(String alignment) {
		itemCard.put("itemListAlignment",alignment);
		return this;
	}
	
	public ItemCard itemListSummary(ItemListSummary summary) {
		itemCard.put("itemListSummary",summary.build());
		return this;
	}
	
	public ItemCard title(String title) {
		itemCard.put("title",title);
		return this;
	}
	
	public ItemCard description(String description) {
		itemCard.put("description",description);
		return this;
	}
	
	public ItemCard buttons(Button button) {
		buttons.add(button.build());
		return this;
	}
	
	public ItemCard buttonLayout(String layout) {
		itemCard.put("buttonLayout",layout);
		return this;
	}
	
	public ItemCard forwardable(boolean forward) {
		itemCard.put("forwardable",forward);
		return this;
	}
	
	public JSONObject build() {
		return this.itemCard;
	}
}
