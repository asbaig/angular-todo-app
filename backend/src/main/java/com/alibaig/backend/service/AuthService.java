package com.alibaig.backend.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alibaig.backend.dto.AuthRequest;
import com.alibaig.backend.dto.AuthResponse;
import com.alibaig.backend.model.User;
import com.alibaig.backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
  
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;

  public AuthResponse register(AuthRequest request) {
    User user = new User();
    user.setUsername(request.getUsername());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    userRepository.save(user);

    String token = jwtService.generateToken(user.getUsername());

    return new AuthResponse(token, user.getUsername());
  }

  public AuthResponse login(AuthRequest request) {
    User user = userRepository.findByUsername(request.getUsername())
        .orElseThrow(() -> new RuntimeException("User not found"));

    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
      throw new RuntimeException("Invalid password");
    }

    String token = jwtService.generateToken(user.getUsername());

    return new AuthResponse(token, user.getUsername());
  }
}
