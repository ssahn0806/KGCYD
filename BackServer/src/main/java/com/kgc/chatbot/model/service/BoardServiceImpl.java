
package com.kgc.chatbot.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kgc.chatbot.model.dao.BoardDAO;
import com.kgc.chatbot.model.dto.Board;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDao;
	
	
	@Override
	public List<Board> getBoards(boolean isEvent) {
		return boardDao.selectBoards(isEvent);
	}

	@Override
	public Board getBoard(int articleNo) {
		return boardDao.selectBoard(articleNo);
	}

	@Override
	public boolean registBoard(Board board) {
		return boardDao.insertBoard(board)>0;
	}

	@Override
	public boolean removeBoard(int articleNo) {
		return boardDao.deleteBoard(articleNo)>0;
	}

	@Override
	public boolean modifyBoard(Board board) {
		return boardDao.updateBoard(board)>0;
	}


}
