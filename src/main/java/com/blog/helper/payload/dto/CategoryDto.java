package com.blog.helper.payload.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class CategoryDto implements Serializable {
    private int categoryId;
    private String categoryTitle;
    private String categoryDescription;
}