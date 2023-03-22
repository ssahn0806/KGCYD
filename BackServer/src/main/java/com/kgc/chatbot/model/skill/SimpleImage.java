package com.kgc.chatbot.model.skill;

import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public class SimpleImage {
	private final JSONObject simpleImage = new JSONObject();
	
	public SimpleImage imageUrl(String imageUrl) {
		simpleImage.put("imageUrl",imageUrl);
		return this;
	}
	
	public SimpleImage altText(String altText) {
		simpleImage.put("altText",altText);
		return this;
	}
	public JSONObject build() {
		return this.simpleImage;
	}
}
