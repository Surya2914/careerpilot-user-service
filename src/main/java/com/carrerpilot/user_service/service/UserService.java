package com.carrerpilot.user_service.service;

import com.carrerpilot.user_service.dto.LoginRequest;
import com.carrerpilot.user_service.dto.LoginResponse;
import com.carrerpilot.user_service.dto.RegisterRequest;
import com.carrerpilot.user_service.entity.User;
import com.carrerpilot.user_service.repository.UserRepository;
import com.carrerpilot.user_service.security.JwtService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService=jwtService;
    }

    public String registerUser(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email is already registered");
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER");

        userRepository.save(user);

        return "User registered successfully";
    }

    public LoginResponse loginUser(LoginRequest request) {

    User user = userRepository.findByEmail(request.getEmail());

    if (user == null) {
        throw new RuntimeException("Invalid email or password");
    }

    boolean passwordMatches = passwordEncoder.matches(
            request.getPassword(),
            user.getPassword()
    );

    if (!passwordMatches) {
        throw new RuntimeException("Invalid email or password");
    }

    String token = jwtService.generateToken(user.getEmail());

    return new LoginResponse(
        user.getId(),
        token,
        "Bearer",
        user.getEmail()
);
}
}