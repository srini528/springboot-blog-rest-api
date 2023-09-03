package com.springboot.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.BlogAPIException;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.CommentDTO;
import com.springboot.blog.payload.PostDTO;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.CommentService;
@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private ModelMapper mapper;
	@Autowired
	private CommentRepository commentRepo;
	@Autowired
	private PostRepository postRepo;
	@Override
	public CommentDTO createComment(long postId, CommentDTO dto) {
		Comment comment=mapToEntity(dto);
		// TODO Auto-generated method stub
		Post post=postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));
		
		comment.setPost(post);
		
		Comment newComment=commentRepo.save(comment);
		return mapToDto(newComment);
	}
	
	private CommentDTO mapToDto(Comment dto) {
		CommentDTO comDto=mapper.map(dto, CommentDTO.class);
		
		return comDto;
	}
	
	private Comment mapToEntity(CommentDTO dto) {
		Comment comDto=mapper.map(dto, Comment.class);
		
		return comDto;
	}

	@Override
	public List<CommentDTO> getCommentByPostId(long postId) {
		// TODO Auto-generated method stub
		List<Comment> comments=commentRepo.findByPostId(postId);
		
		return comments.stream().map(comment->mapToDto(comment)).collect(Collectors.toList());
	}

	@Override
	public CommentDTO getCommentById(long postId,long commentId) {
		// TODO Auto-generated method stub
		Post post=postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));
		
		Comment comment=commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "commentId", commentId));
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Given comment id does not belogns to post");
		}
		return mapToDto(comment);
	}

	@Override
	public CommentDTO updateComment(long postId, long commentId,CommentDTO dto) {
		// TODO Auto-generated method stub
        Post post=postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));
		
		Comment comment=commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "commentId", commentId));
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Given comment id does not belogns to post");
		}
		
		comment.setName(dto.getName());
		comment.setEmail(dto.getEmail());
		comment.setBody(dto.getBody());
		
		Comment newComment=commentRepo.save(comment);
		
		return mapToDto(newComment);
	}

	@Override
	public void deleteCommentById(long postId, long commentId) {
		// TODO Auto-generated method stub
        Post post=postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));
		
		Comment comment=commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "commentId", commentId));
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Given comment id does not belogns to post");
		}
		commentRepo.delete(comment);
	}

}
