package com.kgc.chatbot.model.service;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kgc.chatbot.jpa.entity.ClientLog;
import com.kgc.chatbot.jpa.repo.ClientLogRepository;

@Service
public class ClientLogServiceImpl implements ClientLogService {
	@Autowired
	private ClientLogRepository clientLogRepository;
	
	@Override
	public boolean saveClientLog(ClientLog log) {
		clientLogRepository.save(log);
		return true;
	}

	@Override
	public ClientLog createClientLog(JSONObject user, JSONObject block, JSONObject extra, String lastJoin) {
		String plusfriendUserKey = String.valueOf(user.get("plusfriendUserKey"));
		String blockId = String.valueOf(block.get("id"));
		String blockName = String.valueOf(block.get("name"));
		
		return ClientLog.builder().plusfriendUserKey(plusfriendUserKey).blockId(blockId).blockName(blockName).clientExtra(extra.toJSONString()).executeTime(lastJoin).build();
	}

	@Override
	public List<ClientLog> findAllClientLog() {
		return clientLogRepository.findAll();
	}

}
