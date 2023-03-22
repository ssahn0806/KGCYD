package com.kgc.chatbot.model.skill;

import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public class Profile {

	private final JSONObject profile = new JSONObject();
	
	
	public Profile() {
		profile.put("imageUrl","http://devsu.iptime.org:9000/api/products/image/profile.png");
	}
	
	public Profile nickname(String nickname) {
		profile.put("nickname",nickname);
		return this;
	}
	
	public Profile imageUrl(String imageUrl) {
		profile.put("imageUrl",imageUrl);
		return this;
	}
	
	public Profile width(int width) {
		profile.put("width",width);
		return this;
	}
	
	public Profile height(int height) {
		profile.put("height",height);
		return this;
	}
	
	public Profile title(String title) {
		profile.put("title",title);
		return this;
	}
	
	public JSONObject build() {
		return this.profile;
	}
}
