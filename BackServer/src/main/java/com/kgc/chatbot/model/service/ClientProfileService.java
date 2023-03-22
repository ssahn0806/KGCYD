package com.kgc.chatbot.model.service;

import org.json.simple.JSONObject;

import com.kgc.chatbot.model.skill.ClientProfile;

public interface ClientProfileService {
	ClientProfile getOtpData(JSONObject profile);
	
}
