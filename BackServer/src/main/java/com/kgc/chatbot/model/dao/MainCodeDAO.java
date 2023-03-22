package com.kgc.chatbot.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kgc.chatbot.model.dto.MainCode;

@Mapper
public interface MainCodeDAO {
	
	List<MainCode> selectMainCodes();
	
	MainCode selectMainCode(String mainCode);
	
	int insertMainCode(MainCode mainCode);
	
	int updateMainCode(MainCode mainCode);
	
	int deleteMainCode(String mainCode);
	
}
