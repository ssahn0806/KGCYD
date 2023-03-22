package com.kgc.chatbot.model.skill;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public class Carousel {
	
	private final JSONObject carousel = new JSONObject();
	private final JSONArray items = new JSONArray();
	
	public Carousel() {
		carousel.put("items",items);
	}
	public Carousel type(String type) {
		carousel.put("type",type);
		return this;
	}
	
	public Carousel items(JSONObject item) {
		items.add(item);
		return this;
	}
	
	public Carousel header(CarouselHeader header) {
		carousel.put("header",header.build());
		return this;
	}
	
	public JSONObject build() {
		return this.carousel;
	}
}
