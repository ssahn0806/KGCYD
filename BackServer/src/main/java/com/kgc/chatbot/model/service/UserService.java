package com.kgc.chatbot.model.service;

import com.kgc.chatbot.model.dto.User;

public interface UserService {
	
	User login(User user);
	User selectUser(String userid);
	void saveRefreshToken(String userid,String refreshToken);
	Object getRefreshToken(String userid);
	void deleteRefreshToken(String userid);
	
	boolean registUser(User user);
	boolean modifyUser(User user);
	boolean removeUser(String userid);
}
