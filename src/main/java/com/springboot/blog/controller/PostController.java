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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.payload.PostDTO;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;
import com.springboot.blog.utility.AppConstants;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	@Autowired
	private PostService service;
	@PostMapping
	public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO dto){
		PostDTO res=service.createPost(dto);
		return new ResponseEntity<PostDTO>(res,HttpStatus.CREATED);
	}
	
	@GetMapping
	public PostResponse getAllPosts(
			@RequestParam(value="pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
			@RequestParam(value="pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
			@RequestParam(value="sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
			@RequestParam(value="sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIRECTION,required = false) String sortDir
			){
		
		return service.getAllPosts(pageNo, pageSize,sortBy,sortDir);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PostDTO> getPostById(@PathVariable(name="id") long id){
		
		PostDTO dto=service.getPostById(id);
		return ResponseEntity.ok(dto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PostDTO> updatePost(@Valid @RequestBody PostDTO dto,@PathVariable(name="id")long id){
		PostDTO newdto=service.updatePostById(dto, id);
		return ResponseEntity.ok(newdto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePostById(@PathVariable(name="id") long id){
		
		service.deletePostById(id);
		return ResponseEntity.ok("Post is deleted successfully");
	}

}
