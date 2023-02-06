package com.blog.service;

import com.blog.helper.exception.ResourceNotFoundException;
import com.blog.helper.payload.CategoryDto;
import com.blog.helper.payload.PostDto;
import com.blog.helper.payload.PostResponse;
import com.blog.helper.payload.UserDto;
import com.blog.model.Category;
import com.blog.model.Post;
import com.blog.model.User;
import com.blog.repository.CategoryRepository;
import com.blog.repository.PostRepository;
import com.blog.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository postRepo;
    private final ModelMapper mapper;
    private final UserRepository userRepo;
    private final CategoryRepository categoryRepo;

    //create
    public PostDto save(PostDto postDto, int userId, int categoryId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " UserID", userId));
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", " CategoryID", categoryId));

        postDto.setImageName("default.png");
        postDto.setCreatedDate(new Date());
        postDto.setUser(mapper.map(user, UserDto.class));
        postDto.setCategory(mapper.map(category, CategoryDto.class));
        Post save = postRepo.save(mapper.map(postDto, Post.class));
        return mapper.map(save, PostDto.class);
    }

    //update
    public PostDto save(PostDto postDto, int categoryId) {
        Post post = postRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Post", " PostID", categoryId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        if (postDto.getImageName() != null)
            post.setImageName(postDto.getImageName());
        Post save = postRepo.save(post);
        return mapper.map(save, PostDto.class);
    }

    //delete
    public void delete(int postId) {
        Post category = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", " PostID", postId));
        postRepo.delete(category);
    }

    //get single
    public PostDto findById(int postId) {
        Post category = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", " PostID", postId));
        return mapper.map(category, PostDto.class);
    }

    //get all
    public PostResponse findAll(int pageNumber, int pageSize, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> postPage = postRepo.findAll(pageable);
        List<Post> posts = postPage.getContent();
        List<PostDto> postDtoList = posts.stream().map((post) -> mapper.map(post, PostDto.class)).collect(toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtoList);
        postResponse.setPageNumber(postPage.getNumber());
        postResponse.setPageSize(postPage.getSize());
        postResponse.setTotalElements(postPage.getTotalElements());
        postResponse.setTotalPages(postPage.getTotalPages());
        postResponse.setLastPage(postPage.isLast());
        return postResponse;
    }

    //get all by "user"
    public List<PostDto> findAllByUser(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " UserID", userId));
        return postRepo.findByUser(user).stream().map((post) -> mapper.map(post, PostDto.class)).collect(toList());
    }

    //get all by "category"
    public List<PostDto> findAllByCategory(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", " CategoryID", categoryId));
        return postRepo.findByCategory(category).stream().map((post) -> mapper.map(post, PostDto.class)).collect(toList());
    }

    //search posts
    public List<PostDto> findByTitle(String keyword) {
        List<Post> foundPosts = postRepo.findByTitleContaining(keyword);
        return foundPosts.stream().map((posts) -> mapper.map(foundPosts, PostDto.class)).collect(toList());
    }
}