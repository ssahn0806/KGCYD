package com.kgc.chatbot.model.skill;

import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public class CarouselHeader {
	
	private final JSONObject carouselHeader = new JSONObject();
	
	public CarouselHeader title(String title) {
		carouselHeader.put("title",title);
		return this;
	}
	
	public CarouselHeader description(String description) {
		carouselHeader.put("description",description);
		return this;
	}
	
	public CarouselHeader thumbnail(Thumbnail thumbnail) {
		carouselHeader.put("thumbnail",thumbnail.build());
		return this;
	}
	
	public JSONObject build() {
		return this.carouselHeader;
	}
}
