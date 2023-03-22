package com.kgc.chatbot.model.skill;

import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public class Thumbnail {
	private final JSONObject thumbnail = new JSONObject();
	
//	public Thumbnail() {
//		thumbnail.put("fixedRatio",false);
//	}
	
	public Thumbnail imageUrl(String imageUrl) {
		thumbnail.put("imageUrl",imageUrl);
		return this;
	}
	
	public Thumbnail link(Link link) {
		thumbnail.put("link",link.build());
		return this;
	}
	
	public Thumbnail fixedRatio(boolean fixedRatio) {
		thumbnail.put("fixedRatio",fixedRatio);
		return this;
	}
	
	public Thumbnail width(int width) {
		thumbnail.put("width",width);
		return this;
	}
	
	public Thumbnail height(int height) {
		thumbnail.put("height",height);
		return this;
	}
	
	public JSONObject build() {
		return this.thumbnail;
	}
}
