package com.kgc.chatbot.model.skill;

import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public class Social {
	
	private final JSONObject social = new JSONObject();
	
	public Social like(int like) {
		social.put("like",like);
		return this;
	}
	
	public Social comment(int comment) {
		social.put("comment",comment);
		return this;
	}
	
	public Social share(int share) {
		social.put("share",share);
		return this;
	}
	
	public JSONObject build() {
		return this.social;
	}
}
