package com.springboot.blog.payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
	private List<PostDTO> content;
	private int pageNo;
	private int pageSize;
	private long totalElements;
	private boolean last;
	private int totalPages;
	

}
