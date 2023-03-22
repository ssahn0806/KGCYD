package com.kgc.chatbot.interceptor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.kgc.chatbot.jpa.entity.ClientInfo;
import com.kgc.chatbot.jpa.entity.ClientLog;
import com.kgc.chatbot.model.service.ClientInfoService;
import com.kgc.chatbot.model.service.ClientLogService;

@Component
public class LoggingInterceptor implements HandlerInterceptor{

	@Autowired
	private ClientInfoService clientInfoService;
	@Autowired
	private ClientLogService clientLogService;
	
	@Override
	 public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		
		ContentCachingRequestWrapper httpServletRequest = (ContentCachingRequestWrapper) request;
	    ContentCachingResponseWrapper httpServletResponse = (ContentCachingResponseWrapper) response;
		String url = httpServletRequest.getRequestURI();
        String reqContent = new String(httpServletRequest.getContentAsByteArray());
    	
//    	String resContent = new String(httpServletResponse.getContentAsByteArray());
//    	int httpStatus = httpServletResponse.getStatus();
    	
    	JSONParser jsonParser = new JSONParser();
    	JSONObject res = (JSONObject)jsonParser.parse(reqContent);

    	JSONObject clientExtra = (JSONObject)((JSONObject)res.get("action")).get("clientExtra");

    	JSONObject userReq = (JSONObject)res.get("userRequest");
    	JSONObject user = (JSONObject)((JSONObject)userReq.get("user")).get("properties");
    	JSONObject block = (JSONObject)userReq.get("block");
		
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
    	String lastJoin = sdf.format(new Date());
    	
    	System.out.println(userReq);
    	//사용자 접속 최종 기록 업데이트
		ClientInfo info = clientInfoService.createClientInfo(user, lastJoin);
		clientInfoService.saveClientInfo(info);
		
		//사용자 로그 등록 - 조회 제품 리스트, 상세 정보만 로그 수집
		if(url.equals("/api/chat/products/list") || url.equals("/api/chat/products/detail")) {
			ClientLog log = clientLogService.createClientLog(user, block, clientExtra, lastJoin);
			clientLogService.saveClientLog(log);			
		}
        	
	}
}
