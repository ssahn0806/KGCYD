package com.kgc.chatbot.model.service;

import org.json.simple.JSONObject;

public interface SmsService {
	JSONObject pushSMS(String number, String content) throws Exception;
}
