package com.springboot.blog.service.impl;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.PostDTO;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository repo;
	@Override
	public PostDTO createPost(PostDTO dto) {
		// TODO Auto-generated method stub
		Post newPost=repo.save(convertToEntity(dto));
		return convertToDTO(newPost);
		
	}
	private PostDTO convertToDTO(Post newPost) {
		PostDTO responseDTO=new PostDTO();
		responseDTO.setId(newPost.getId());
		responseDTO.setTitle(newPost.getTitle());
		responseDTO.setDescription(newPost.getDescription());
		responseDTO.setContent(newPost.getContent());
		return responseDTO;
	}
	
	private Post convertToEntity(PostDTO dto) {
        Post post=new Post();
		
		post.setId(dto.getId());
		post.setTitle(dto.getTitle());
		post.setDescription(dto.getDescription());
		post.setContent(dto.getContent());
		
		return post;
	}
	
	@Override
	public PostResponse getAllPosts(int pageNo,int pageSize,String sortBy,String sortDir) {
		// TODO Auto-generated method stub
	//Pageable page=PageRequest.of(pageNo, pageSize);
		
		Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending()
				:Sort.by(sortBy).descending();		
		Page<Post> pages=repo.findAll(PageRequest.of(pageNo, pageSize,sort));
		List<Post> posts=pages.getContent();
		List<PostDTO>content= posts.stream().map(post->convertToDTO(post)).collect(Collectors.toList());
		PostResponse response=new PostResponse();
		response.setContent(content);
		response.setPageNo(pages.getNumber());
		response.setPageSize(pages.getSize());
		response.setTotalElements(pages.getTotalElements());
		response.setTotalPages(pages.getTotalPages());
		response.setLast(pages.isLast());
		return response;
	}
	@Override
	public PostDTO getPostById(long id) {
		// TODO Auto-generated method stub
		Post post=repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Post", "id", id));
		return convertToDTO(post);
	}
	@Override
	public PostDTO updatePostById(PostDTO dto, long id) {
		// TODO Auto-generated method stub
		Post post=repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Post", "id", id));
		post.setTitle(dto.getTitle());
		post.setDescription(dto.getDescription());
		post.setContent(dto.getContent());
		Post newPost=repo.save(post);
		return convertToDTO(newPost);
	}
	@Override
	public void deletePostById(long id) {
		// TODO Auto-generated method stub
		Post post=repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Post", "id", id));
		repo.delete(post);
		
	}

}
