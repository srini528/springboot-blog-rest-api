package com.springboot.blog.payload;

import java.util.Set;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostDTO {
	private long id;
	@NotEmpty
	@Size(min = 4,message = "Title should be min 4 chars." )
	private String title;
	@Min(value = 5)
	@Max(value = 10)
	private String description;
	@NotNull
	private String content;
	private Set<CommentDTO> comments;

}
