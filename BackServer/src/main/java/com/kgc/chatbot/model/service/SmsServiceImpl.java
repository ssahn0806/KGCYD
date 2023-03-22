package com.kgc.chatbot.model.service;

import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.kgc.chatbot.model.skill.SmsResponseBuilder;

@Service
public class SmsServiceImpl implements SmsService {

	@Value("${NAVER_API_URL}")
	String smsURL;
	@Value("${NAVER_API_KEY}")
	String smsKey;
	@Value("${NAVER_ACCESS}")
	String accessKey;
	@Value("${NAVER_SECRET}")
	String secretKey;
	
	RestTemplate restTemplate;
	
	@Override
	public JSONObject pushSMS(String number, String content) throws Exception {
		restTemplate = new RestTemplate();
		
		String now = String.valueOf(new Date().getTime());
		HttpHeaders header = new HttpHeaders();
		header.set("Content-Type", MediaType.APPLICATION_JSON_UTF8.toString());
		header.set("x-ncp-apigw-timestamp",now);
		header.set("x-ncp-iam-access-key",accessKey);
		header.set("x-ncp-apigw-signature-v2",makeSignature(now));
		
		SmsResponseBuilder srb = new SmsResponseBuilder();
		srb.addReceiver(number);
		srb.addContent(content);
		
		RequestEntity<JSONObject> req = RequestEntity
				.post(UriComponentsBuilder.fromUriString(smsURL+smsKey).build().toUri())
				.headers(header)
				.body(srb.build());
				
		ResponseEntity<JSONObject> res = restTemplate.exchange(req,JSONObject.class);
		
		return res.getBody();
	}

	public String makeSignature(String now) throws Exception {
		String space = " ";					// one space
		String newLine = "\n";					// new line
		String method = "POST";					// method
		String url = smsKey;	// url (include query string)
		String timestamp = now;			// current timestamp (epoch)

		String message = new StringBuilder()
			.append(method)
			.append(space)
			.append(url)
			.append(newLine)
			.append(timestamp)
			.append(newLine)
			.append(accessKey)
			.toString();

		SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
		Mac mac = Mac.getInstance("HmacSHA256");
		mac.init(signingKey);

		byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
		String encodeBase64String = Base64.encodeBase64String(rawHmac);

	  return encodeBase64String;
	}
}
