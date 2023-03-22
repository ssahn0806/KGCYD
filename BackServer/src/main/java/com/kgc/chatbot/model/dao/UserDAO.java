package com.kgc.chatbot.model.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kgc.chatbot.model.dto.User;

@Mapper
public interface UserDAO {
	User login(User user);
	User selectUser(String userid);
	void saveRefreshToken(Map<String,String> map);
	Object getRefreshToken(String userid);
	void deleteRefreshToken(Map<String,String> map);
	int insertUser(User user);
	int updateUser(User user);
	int deleteUser(String userid);
	
}
