package com.blog.controller;

import com.blog.helper.payload.AuthenticationRequest;
import com.blog.helper.payload.AuthenticationResponse;
import com.blog.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private AuthService authService;

    @PostMapping({"/generate-token","/login"})
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authService.login(authenticationRequest));
    }
}