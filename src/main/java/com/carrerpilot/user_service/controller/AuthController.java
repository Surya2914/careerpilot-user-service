package com.carrerpilot.user_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.carrerpilot.user_service.dto.LoginRequest;
import com.carrerpilot.user_service.dto.LoginResponse;
import com.carrerpilot.user_service.dto.RegisterRequest;
import com.carrerpilot.user_service.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController 
{
    private final UserService userService;

    public AuthController(UserService userService)
    {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String registerUser(@Valid @RequestBody RegisterRequest request)
    {
            return userService.registerUser(request);
    }


    @PostMapping("/login")
    public LoginResponse loginUser(@Valid @RequestBody LoginRequest request) {
          return userService.loginUser(request);
}

@GetMapping("/test")
public String testProtectedEndpoint() {
    return "JWT authentication successful!";
}

@GetMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public String adminEndpoint() {
    return "Welcome, Admin!";
}
    
}
