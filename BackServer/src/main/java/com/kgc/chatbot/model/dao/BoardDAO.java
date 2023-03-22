package com.kgc.chatbot.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kgc.chatbot.model.dto.Board;

@Mapper
public interface BoardDAO {
	List<Board> selectBoards(boolean isEvent);
	
	Board selectBoard(int articleNo);
	
	int insertBoard(Board board);
	
	int deleteBoard(int articleNo);
	
	int updateBoard(Board board);
	
}
