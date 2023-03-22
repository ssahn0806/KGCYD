package com.kgc.chatbot.model.service;

import java.util.List;

import com.kgc.chatbot.model.dto.Board;

public interface BoardService {
	
	List<Board> getBoards(boolean isEvent);
	
	Board getBoard(int articleNo);
	
	boolean registBoard(Board board);
	
	boolean removeBoard(int articleNo);
	
	boolean modifyBoard(Board board);
	
}
