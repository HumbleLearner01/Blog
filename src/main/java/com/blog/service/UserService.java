package com.blog.service;

import com.blog.helper.exception.ResourceNotFoundException;
import com.blog.helper.payload.dto.UserDto;
import com.blog.model.User;
import com.blog.repository.UserRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Data
@Slf4j
public class UserService {
    private final UserRepository userRepo;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    //create user
    public UserDto save(UserDto userDto) {
        User user = dtoToUserEntity(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User save = userRepo.save(user);
        return userEntityToDto(save);
    }

    //update user
    public UserDto save(UserDto userDto, int userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setAbout(userDto.getAbout());
        return userEntityToDto(user);
    }

    //find user by id
    public UserDto findById(int userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "  ID ", userId));
        return userEntityToDto(user);
    }

    //find all users
    public List<UserDto> findAll() {
        return userRepo.findAll().stream().map(this::userEntityToDto).collect(toList());
    }

    //delete user by id
    public void delete(int userId) {
        userRepo.delete(userRepo.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User", " ID ", userId)));
    }


    /*mapping of dto and entity*/
    private User dtoToUserEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    private UserDto userEntityToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }
}