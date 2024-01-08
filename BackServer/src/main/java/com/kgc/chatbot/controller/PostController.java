package com.kgc.chatbot.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/post")
@RestController
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@GetMapping("/{postNo}")
	public void redirectPost(@PathVariable long postNo, HttpServletResponse res) throws IOException {
		System.out.println(postService.redirectPost(postNo));
		res.sendRedirect(postService.redirectPost(postNo));
		
	}
}
