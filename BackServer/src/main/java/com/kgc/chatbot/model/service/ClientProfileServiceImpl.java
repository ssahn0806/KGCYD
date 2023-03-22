package com.kgc.chatbot.model.service;

import java.net.URI;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.kgc.chatbot.model.skill.ClientProfile;

@Service
public class ClientProfileServiceImpl implements ClientProfileService {
	
	@Value("${REST_API_KEY}")
	String restApiKey;
	
	RestTemplate restTemplate;
	StringBuilder sb;
	
	@Override
	public ClientProfile getOtpData(JSONObject profile) {
		String otp = (String)profile.get("otp");
		sb = new StringBuilder();
		sb.append(otp).append("?rest_api_key=").append(restApiKey);
		
		restTemplate = new RestTemplate();
		ResponseEntity<ClientProfile> res = restTemplate.getForEntity(URI.create(sb.toString()), ClientProfile.class);
		
		ClientProfile prop =  res.getBody();
		prop.setPhone_number(prop.getPhone_number().replace("+82 ","0"));
		return prop;
	}

}
