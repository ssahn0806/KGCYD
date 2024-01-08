package com.kgc.chatbot.model.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

	@Value("${POST_ADDR}")
	String postPath;
	
	final String postfix = "?action=add_channel";
	static StringBuilder sb = new StringBuilder();
	
	@Override
	public String redirectPost(long postNo) {
		sb.setLength(0);
		return sb.append(postPath).append(postNo).append(postfix).toString();
	}

}
