package com.blog.service;

import com.blog.config.UserDetailsServiceImpl;
import com.blog.config.jwt.JwtUtil;
import com.blog.helper.payload.AuthenticationRequest;
import com.blog.helper.payload.AuthenticationResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AuthService {
    private JwtUtil jwtUtil;
    private AuthenticationManager authenticationManager;
    private UserDetailsServiceImpl userDetailsService;

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        String token = null;
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getEmail(),
                    authenticationRequest.getPassword()
            ));
            UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
            token = jwtUtil.generateToken(userDetails);
            log.info(token);
        } catch (AuthenticationException e) {
            log.info("authentication failed with provided information: {}", authenticationRequest);
            e.printStackTrace();
        }
        return new AuthenticationResponse(authenticationRequest.getEmail(), token);
    }
}