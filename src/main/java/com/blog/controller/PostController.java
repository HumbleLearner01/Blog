package com.blog.controller;

import com.blog.helper.constants.AppConstants;
import com.blog.helper.payload.ApiResponse;
import com.blog.helper.payload.PostDto;
import com.blog.helper.payload.PostResponse;
import com.blog.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class PostController {
    private final PostService postService;

    //create
    @PostMapping("/user/{userId}/category/{categoryId}/post")
    public ResponseEntity<PostDto> save(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId) {
        return new ResponseEntity<>(postService.save(postDto, userId, categoryId), HttpStatus.CREATED);
    }

    //update
    @PutMapping("/post/{postId}")
    public ResponseEntity<PostDto> save(@RequestBody PostDto postDto, @PathVariable Integer postId) {
        return new ResponseEntity<>(postService.save(postDto, postId), HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/post/{postId}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer postId) {
        postService.delete(postId);
        return new ResponseEntity<>(new ApiResponse("Post deleted successfully!", true), HttpStatus.OK);
    }

    //get single
    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDto> findById(@PathVariable Integer postId) {
        return new ResponseEntity<>(postService.findById(postId), HttpStatus.OK);
    }

    //get all
    @GetMapping("/post")
    public ResponseEntity<PostResponse> findAll(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDirection", defaultValue = AppConstants.SORT_DIRECTION, required = false) String sortDirection) {
        return new ResponseEntity<>(postService.findAll(pageNumber, pageSize, sortBy, sortDirection), HttpStatus.OK);
    }

    //get all by "user"
    @GetMapping("/user/{userId}/post")
    public ResponseEntity<List<PostDto>> findAllByUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(postService.findAllByUser(userId));
    }

    //get all by "category"
    @GetMapping("/category/{categoryId}/post")
    public ResponseEntity<List<PostDto>> findAllByCategory(@PathVariable Integer categoryId) {
        return ResponseEntity.ok(postService.findAllByCategory(categoryId));
    }

    //search posts by "title"
    @GetMapping("/post/search/{keyword}")
    public ResponseEntity<List<PostDto>> findByPost(@PathVariable("keyword") String keyword) {
        return ResponseEntity.ok(postService.findByTitle(keyword));
    }
}