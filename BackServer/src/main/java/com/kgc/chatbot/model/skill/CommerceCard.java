package com.kgc.chatbot.model.skill;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public class CommerceCard {

	private final String currency = "won";
	private final JSONObject commerceCard = new JSONObject();
	private final JSONArray thumbnails = new JSONArray();
	private final JSONArray buttons = new JSONArray();
	public CommerceCard() {
		commerceCard.put("currency",currency);
		commerceCard.put("thumbnails",thumbnails);
		commerceCard.put("buttons",buttons);
	}
	public CommerceCard description(String description) {
		commerceCard.put("description",description);
		return this;
	}
	
	public CommerceCard price(int price) {
		commerceCard.put("price",price);
		return this;
	}
	
	public CommerceCard discount(int discount) {
		commerceCard.put("discount",discount);
		return this;
	}
	
	public CommerceCard discountRate(int discountRate) {
		commerceCard.put("discountRate",discountRate);
		return this;
	}
	
	public CommerceCard discountedPrice(int discountedPrice) {
		commerceCard.put("discountedPrice",discountedPrice);
		return this;
	}
	
	public CommerceCard thumbnails(Thumbnail thumbnail) {
		thumbnails.add(thumbnail.build());
		return this;
	}
	
	public CommerceCard profile(Profile profile) {
		commerceCard.put("profile",profile.build());
		return this;
	}
	
	public CommerceCard buttons(Button button) {
		buttons.add(button.build());
		return this;
	}
	
	public JSONObject build() {
		return this.commerceCard;
	}
}
