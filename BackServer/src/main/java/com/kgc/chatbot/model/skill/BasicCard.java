package com.kgc.chatbot.model.skill;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public class BasicCard {

	private final JSONArray buttons = new JSONArray();
	private final JSONObject basicCard = new JSONObject();
	
	public BasicCard title(String title) {
		basicCard.put("title",title);
		basicCard.put("buttons",buttons);
		return this;
	}
	
	public BasicCard description(String description) {
		basicCard.put("description",description);
		return this;
	}
	
	public BasicCard thumbnail(Thumbnail thumbnail) {
		basicCard.put("thumbnail",thumbnail.build());
		return this;
	}
	
	public BasicCard profile(Profile profile) {
		basicCard.put("profile",profile.build());
		return this;
	}
	
	public BasicCard social(Social social) {
		basicCard.put("social",social.build());
		return this;
	}
	
	public BasicCard buttons(Button button) {
		buttons.add(button.build());
		return this;
	}
	
	public BasicCard forwardable(boolean flag) {
		basicCard.put("forwardable",flag);
		return this;
	}
	
	public JSONObject build() {
		return this.basicCard;
	}
	
}
