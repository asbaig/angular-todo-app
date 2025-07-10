package com.alibaig.backend.util;

import org.springframework.security.core.context.SecurityContextHolder;

import com.alibaig.backend.model.User;
import com.alibaig.backend.security.CustomUserDetails;

public class UserUtils {
  public static User getCurrentUser() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal instanceof CustomUserDetails customUserDetails) {
      return customUserDetails.getUser();
    }

    throw new RuntimeException("User not authenticated");
  } 
}
