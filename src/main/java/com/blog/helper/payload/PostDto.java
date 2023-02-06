package com.blog.helper.payload;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class PostDto implements Serializable {
    private int postId;
    private String title;
    private String content;
    private String imageName;
    private Date createdDate;

    private CategoryDto category;
    private UserDto user;
}