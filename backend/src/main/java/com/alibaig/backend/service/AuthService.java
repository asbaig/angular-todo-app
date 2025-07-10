package com.alibaig.backend.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alibaig.backend.dto.AuthRequest;
import com.alibaig.backend.dto.AuthResponse;
import com.alibaig.backend.exception.InvalidPasswordException;
import com.alibaig.backend.exception.UsernameAlreadyExistsException;
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
    if (userRepository.findByUsername(request.getUsername()).isPresent()) {
      throw new UsernameAlreadyExistsException("Username already exists");
    }

    User user = new User();
    user.setUsername(request.getUsername());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    userRepository.save(user);

    String token = jwtService.generateToken(user.getUsername());

    return new AuthResponse(token, user.getUsername());
  }

  public AuthResponse login(AuthRequest request) {
    User user = userRepository.findByUsername(request.getUsername())
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
      throw new InvalidPasswordException("Invalid password");
    }

    String token = jwtService.generateToken(user.getUsername());

    return new AuthResponse(token, user.getUsername());
  }
}
