package com.kgc.chatbot.model.service;

import java.util.List;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kgc.chatbot.jpa.entity.ClientInfo;
import com.kgc.chatbot.jpa.repo.ClientInfoRepository;
import com.kgc.chatbot.model.skill.ClientProfile;

@Service
public class ClientInfoServiceImpl implements ClientInfoService {
	@Autowired
	private ClientInfoRepository clientInfoRepository;
	@Override
	public boolean saveClientInfo(ClientInfo info) {
		clientInfoRepository.save(info);
		return true;
	}

	@Override
	public ClientInfo findClientInfo(String pk) {
		Optional<ClientInfo> target = clientInfoRepository.findById(pk);
		if(!target.isPresent()) {
			return null;
		}
		else {
			return target.get();
		}
	}
	
	@Override
	public ClientInfo findClientByEvent(String id) {
		Optional<ClientInfo> target = clientInfoRepository.findByappUserId(id);
		if(!target.isPresent()) {
			return null;
		}
		else {
			return target.get();
		}
	}

	@Override
	public List<ClientInfo> findAllClientInfo() {
		return clientInfoRepository.findAll();
	}
	
	//조회된 결과가 없는 경우 신규로 추가하기
	@Override
	public ClientInfo createClientInfo(JSONObject user,String lastJoin) {
		String plusfriendUserKey = String.valueOf(user.get("plusfriendUserKey"));
		String appUserId = user.get("appUserId")!=null? String.valueOf(user.get("appUserId")) : "";
		String botUserKey = String.valueOf(user.get("botUserKey"));
		boolean isFriend = user.get("isFriend")!=null? (boolean)user.get("isFriend") : String.valueOf(user.get("appUserStatus")).equals("REGISTERED");
		
		if(findClientInfo(plusfriendUserKey)==null)
			return ClientInfo.builder().plusfriendUserKey(plusfriendUserKey).appUserId(appUserId).botUserKey(botUserKey).isFriend(isFriend).lastJoin(lastJoin).build();
		ClientInfo target = findClientInfo(plusfriendUserKey);
		return ClientInfo.builder().plusfriendUserKey(plusfriendUserKey).appUserId(appUserId).botUserKey(botUserKey).isFriend(isFriend).firstJoin(target.getFirstJoin()).lastJoin(lastJoin).nickName(target.getNickName()).phoneNumber(target.getPhoneNumber()).build();
	}

	@Override
	public ClientInfo createClientInfo(JSONObject user, JSONObject profile, ClientProfile cp) {
		long appUserId = (Long)profile.get("app_user_id");
		String plusfriendUserKey = String.valueOf(user.get("plusfriendUserKey"));
		String botUserKey = String.valueOf(user.get("botUserKey"));
		
		if(findClientInfo(plusfriendUserKey)==null) {
			return ClientInfo.builder().plusfriendUserKey(plusfriendUserKey).appUserId(String.valueOf(appUserId)).botUserKey(botUserKey).nickName(cp.getNickname()).phoneNumber(cp.getPhone_number()).build();
		}
		ClientInfo target = findClientInfo(plusfriendUserKey);
		return ClientInfo.builder().plusfriendUserKey(target.getPlusfriendUserKey()).botUserKey(target.getBotUserKey()).appUserId(target.getAppUserId()).isFriend(target.isFriend()).firstJoin(target.getFirstJoin()).lastJoin(target.getLastJoin()).nickName(cp.getNickname()).phoneNumber(cp.getPhone_number()).build();
	}

}
