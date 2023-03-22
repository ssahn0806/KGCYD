package com.kgc.chatbot.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kgc.chatbot.jpa.entity.ClientInfo;
import com.kgc.chatbot.model.service.ClientInfoService;
import com.kgc.chatbot.model.service.ClientLogService;
import com.kgc.chatbot.model.service.SmsService;

@RestController
@RequestMapping("/clients")
public class ClientController {
	@Autowired
	ClientInfoService clientInfoService;
	@Autowired
	ClientLogService clientLogService;
	@Autowired
	SmsService smsService;
	
	@GetMapping("infos")
	private ResponseEntity<?> getAllClientsInfo(){
		return ResponseEntity.ok(clientInfoService.findAllClientInfo());
	}
	@GetMapping("logs")
	private ResponseEntity<?> getAllClientsLog(){
		return ResponseEntity.ok(clientLogService.findAllClientLog());
	}
	@PostMapping("update")
	private ResponseEntity<?> updateClientInfo(@RequestBody JSONObject obj){
		String id = (String)obj.get("id");
		String event = (String)obj.get("event");
		
		try {
			smsService.pushSMS("01051163268", id + " : " +event);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			//신규 사용자의 경우 plusfrienduserkey를 주지 않기 때문에 테이블에 넣을수가 없음
			//기존에 카카오 로그인을 한 사용자의 상태 변화만 감지 가능하다.
			if(clientInfoService.findClientByEvent(id)!=null) {
				ClientInfo info = clientInfoService.findClientByEvent(id);
	
				if(info.isFriend() && event.equals("blocked")) {
					ClientInfo target = ClientInfo.builder().plusfriendUserKey(info.getPlusfriendUserKey()).botUserKey(info.getBotUserKey()).appUserId(info.getAppUserId()).isFriend(false).nickName(info.getNickName()).phoneNumber(info.getPhoneNumber()).firstJoin(info.getFirstJoin()).lastJoin(info.getLastJoin()).build();
					clientInfoService.saveClientInfo(target);
				}
				if(!info.isFriend() && event.equals("added")) {
					ClientInfo target = ClientInfo.builder().plusfriendUserKey(info.getPlusfriendUserKey()).botUserKey(info.getBotUserKey()).appUserId(info.getAppUserId()).isFriend(true).nickName(info.getNickName()).phoneNumber(info.getPhoneNumber()).firstJoin(info.getFirstJoin()).lastJoin(info.getLastJoin()).build();
					clientInfoService.saveClientInfo(target);
				}
				
			}
		}
		return ResponseEntity.ok().build();
	}
}
