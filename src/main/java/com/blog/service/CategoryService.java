package com.blog.service;

import com.blog.helper.exception.ResourceNotFoundException;
import com.blog.helper.payload.dto.CategoryDto;
import com.blog.model.Category;
import com.blog.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepo;
    private final ModelMapper mapper;

    //create
    public CategoryDto save(CategoryDto categoryDto) {
        Category save = categoryRepo.save(mapper.map(categoryDto, Category.class));
        return mapper.map(save, CategoryDto.class);
    }

    //update
    public CategoryDto save(CategoryDto categoryDto, int categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", " categoryID", categoryId));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        Category save = categoryRepo.save(category);
        return mapper.map(save, CategoryDto.class);
    }

    //delete
    public void delete(int categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", " categoryID", categoryId));
        categoryRepo.delete(category);
    }

    //get single
    public CategoryDto findById(int categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", " categoryID", categoryId));
        return mapper.map(category, CategoryDto.class);
    }

    //get all
    public List<CategoryDto> findAll() {
        List<Category> categoryList = categoryRepo.findAll();
        return categoryList.stream().map((cat)->mapper.map(cat, CategoryDto.class)).collect(toList());
    }
}