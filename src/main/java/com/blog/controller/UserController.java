package com.blog.controller;

import com.blog.helper.payload.ApiResponse;
import com.blog.helper.payload.dto.UserDto;
import com.blog.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    //create a user
    @PostMapping
    public ResponseEntity<UserDto> save(@Valid @RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.save(userDto), HttpStatus.CREATED);
    }

    //update a user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> save(@Valid @RequestBody UserDto userDto, @PathVariable int userId) {
        return new ResponseEntity<>(userService.save(userDto,userId), HttpStatus.OK);
    }

    //delete a user
    //ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> delete(@PathVariable int userId) {
        userService.delete(userId);
        return new ResponseEntity<>(new ApiResponse("User deleted successfully", true), HttpStatus.OK);
    }

    //get a single user
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> findById(@PathVariable int userId) {
        return ResponseEntity.ok(userService.findById(userId));
    }

    //get all the users
    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }
}