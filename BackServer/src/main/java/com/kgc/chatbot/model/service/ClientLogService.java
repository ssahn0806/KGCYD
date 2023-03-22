package com.kgc.chatbot.model.service;

import java.util.List;

import org.json.simple.JSONObject;

import com.kgc.chatbot.jpa.entity.ClientLog;

public interface ClientLogService {
	boolean saveClientLog(ClientLog log);
	
	ClientLog createClientLog(JSONObject user,JSONObject block,JSONObject extra,String lastJoin);
	
	List<ClientLog> findAllClientLog();
}
