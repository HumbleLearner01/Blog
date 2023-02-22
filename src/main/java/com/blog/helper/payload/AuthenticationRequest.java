package com.blog.helper.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class AuthenticationRequest {
    private String email;
    private String password;
}