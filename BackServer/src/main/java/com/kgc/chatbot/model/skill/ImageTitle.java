package com.kgc.chatbot.model.skill;

import org.json.simple.JSONObject;

public class ImageTitle {

	private final JSONObject imageTitle = new JSONObject();
	
	
	public ImageTitle title(String title) {
		imageTitle.put("title",title);
		return this;
	}
	
	public ImageTitle description(String description) {
		imageTitle.put("description",description);
		return this;
	}
	
	public ImageTitle imageUrl(String imageUrl) {
		imageTitle.put("imageUrl",imageUrl);
		return this;
	}
	
	public JSONObject build() {
		return this.imageTitle;
	}
}
