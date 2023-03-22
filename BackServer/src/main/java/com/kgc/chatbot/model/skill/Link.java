package com.kgc.chatbot.model.skill;

import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public class Link {

	private final JSONObject link = new JSONObject();
	
	public Link pc(String pc) {
		link.put("pc",pc);
		return this;
	}
	
	public Link mobile(String mobile) {
		link.put("mobile",mobile);
		return this;
	}
	
	public Link web(String web) {
		link.put("web",web);
		return this;
	}
	
	public JSONObject build() {
		return this.link;
	}
}
