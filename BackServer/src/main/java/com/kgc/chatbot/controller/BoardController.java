package com.kgc.chatbot.controller;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kgc.chatbot.model.dto.Board;
import com.kgc.chatbot.model.service.BoardService;

@RequestMapping("/api/boards")
@RestController
public class BoardController {

	@Value("${FILE_PATH}")
	String folderPath;
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping
	private ResponseEntity<List<Board>> doListBoards(@RequestParam(defaultValue="false") boolean isEvent){
		List<Board> boards = boardService.getBoards(isEvent);
		
		if(boards!=null) {
			return ResponseEntity.ok(boards);
		}
		else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@GetMapping("/{articleNo}")
	private ResponseEntity doDetailBoard(@PathVariable int articleNo) {
		Board board = boardService.getBoard(articleNo);
		
		if(board!=null) {
			return ResponseEntity.ok(board);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping(consumes = {"multipart/form-data"})
	private ResponseEntity doRegistBoard(@RequestPart("board") Board board,@RequestPart(value="upfile",required=false) MultipartFile file) throws IllegalStateException, IOException {
		
		boolean res = boardService.registBoard(board);
		
		if(res) {
			if(file!=null) {
				String originFileName = file.getOriginalFilename();
				String newFileName = "notice_"+board.getArticleNo()+"."+originFileName.substring(originFileName.lastIndexOf(".")+1);
				File upfile = new File(folderPath + newFileName);
				
				file.transferTo(upfile);
				
				board.setThumbnail(newFileName);
				boardService.modifyBoard(board);
			}
			return ResponseEntity.created(URI.create("/api/boards/"+board.getArticleNo())).build();
		}
		else {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	
	@PutMapping(consumes = {"multipart/form-data"})
	private ResponseEntity doModifyBoard(@RequestPart("board") Board board,@RequestPart(value="upfile",required=false) MultipartFile file) throws IllegalStateException, IOException {
		
		Board target = boardService.getBoard(board.getArticleNo());
		
		if(target!=null) {
			String oldFileName = target.getThumbnail();
			String oldFilePath = folderPath + oldFileName;
			
			File oldFile = new File(oldFilePath);
			
			if(oldFile.exists()) {
			   oldFile.delete();	
			   board.setThumbnail("");
			}
			
			if(file!=null) {
				String originFileName = file.getOriginalFilename();
				String newFileName = "notice_"+board.getArticleNo()+"."+originFileName.substring(originFileName.lastIndexOf(".")+1);
				File upfile = new File(folderPath + newFileName);
				
				file.transferTo(upfile);
				
				board.setThumbnail(newFileName);
				
			}
			
			boolean res = boardService.modifyBoard(board);
			if(res) {
				return ResponseEntity.ok(board);
			}
			else {
				return ResponseEntity.internalServerError().build();
			}
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	@DeleteMapping("/{articleNo}")
	private ResponseEntity doRemoveBoard(@PathVariable int articleNo) {
		Board target = boardService.getBoard(articleNo);
		
		if(target!=null) {
			String oldFileName = target.getThumbnail();
			String oldFilePath = folderPath + oldFileName;
			
			File oldFile = new File(oldFilePath);
			
			if(oldFile.exists()) {
			   oldFile.delete();	
			   target.setThumbnail("");
			}
			boolean res = boardService.removeBoard(articleNo);
			if(res) {
				return ResponseEntity.noContent().build();
			}
			else {
				return ResponseEntity.internalServerError().build();
			}
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
}
