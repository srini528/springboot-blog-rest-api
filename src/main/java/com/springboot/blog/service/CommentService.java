package com.springboot.blog.service;

import java.util.List;

import com.springboot.blog.payload.CommentDTO;

public interface CommentService {
	
	CommentDTO createComment(long postId,CommentDTO dto);
	
	List<CommentDTO> getCommentByPostId(long postId);
	CommentDTO getCommentById(long postId,long commentId);
	CommentDTO updateComment(long postId,long commentId,CommentDTO dto);

}
