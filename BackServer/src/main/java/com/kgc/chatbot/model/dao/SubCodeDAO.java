package com.kgc.chatbot.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kgc.chatbot.model.dto.SubCode;

@Mapper
public interface SubCodeDAO {

	List<SubCode> selectSubCodes(String mainCode);
	
	SubCode selectSubCode(String code);
	
	int insertSubCode(SubCode subCode);
	
	int updateSubCode(SubCode subCode);
	
	int deleteSubCode(String code);
	
	int updateCountPlus(String code);
	
	int updateCountMinus(String code);
	
}
