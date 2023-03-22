package com.kgc.chatbot.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kgc.chatbot.model.dao.UserDAO;
import com.kgc.chatbot.model.dto.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDao;
	
	@Override
	public User login(User user) {
		if(user.getUserid() == null || user.getUserpwd() == null)
			return null;
		return userDao.login(user);
	}

	@Override
	public User selectUser(String userid) {
		return userDao.selectUser(userid);
	}

	@Override
	public void saveRefreshToken(String userid, String refreshToken) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", refreshToken);
		userDao.saveRefreshToken(map);

	}

	@Override
	public Object getRefreshToken(String userid) {
		return userDao.getRefreshToken(userid);
	}

	@Override
	public void deleteRefreshToken(String userid) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", null);
		userDao.deleteRefreshToken(map);
	}

	@Override
	public boolean registUser(User user) {
		return userDao.insertUser(user)>0;
	}

	@Override
	public boolean modifyUser(User user) {
		return userDao.updateUser(user)>0;
	}

	@Override
	public boolean removeUser(String userid) {
		return userDao.deleteUser(userid)>0;
	}

}
