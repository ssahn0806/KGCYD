package com.kgc.chatbot.model.service;

import java.util.List;

import org.json.simple.JSONObject;

import com.kgc.chatbot.jpa.entity.ClientInfo;
import com.kgc.chatbot.model.skill.ClientProfile;

public interface ClientInfoService {
	
	boolean saveClientInfo(ClientInfo info);
	
	ClientInfo findClientByEvent(String id);
	
	ClientInfo findClientInfo(String pk);
	
	List<ClientInfo> findAllClientInfo();
	
	ClientInfo createClientInfo(JSONObject user,String lastJoin);

	ClientInfo createClientInfo(JSONObject user, JSONObject profile, ClientProfile cp);
	
}
