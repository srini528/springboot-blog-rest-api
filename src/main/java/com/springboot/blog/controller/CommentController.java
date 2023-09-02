package com.springboot.blog.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.payload.CommentDTO;
import com.springboot.blog.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {
	@Autowired
	private CommentService service;
	
	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<CommentDTO> createComment(@PathVariable(name="postId") long postId,@RequestBody CommentDTO dto){
		CommentDTO dtoRes=service.createComment(postId, dto);
		return new ResponseEntity<CommentDTO>(dtoRes,HttpStatus.CREATED);
	}
	
	@GetMapping("/posts/{postId}/comments")
	public ResponseEntity<List<CommentDTO>> getCommentsByPostId(@PathVariable(value="postId") long postId){
		
		List<CommentDTO> dtoList= service.getCommentByPostId(postId);
		return ResponseEntity.ok(dtoList);
		
	}
	
	@GetMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDTO> getCommentById(@PathVariable(value="postId") long postId, @PathVariable(value="commentId") long commentId){
		
		CommentDTO dto=service.getCommentById(postId, commentId);
		return ResponseEntity.ok(dto);
	}
	@PutMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDTO> updateComment(@PathVariable(value="postId") long postId,@PathVariable(value="commentId") long commentId,@RequestBody CommentDTO dto){
		CommentDTO updatedDto=service.updateComment(postId, commentId, dto);
		
		return ResponseEntity.ok(updatedDto);
	}
	@DeleteMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<String> deleteComment(@PathVariable(value="postId") long postId,@PathVariable(value="commentId") long commentId){
		
		service.deleteCommentById(postId, commentId);
		
		return ResponseEntity.ok("Comment deleted successfully.");
	}
	

}
