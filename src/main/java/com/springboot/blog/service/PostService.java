package com.springboot.blog.service;

import java.util.List;

import com.springboot.blog.payload.PostDTO;
import com.springboot.blog.payload.PostResponse;

public interface PostService {
	
	PostDTO createPost(PostDTO dto);
	PostResponse getAllPosts(int pageNo,int pageSize,String sortBy,String sortDir);
	PostDTO getPostById(long id);
	PostDTO updatePostById(PostDTO dto,long id);
	void deletePostById(long id);
	

}
