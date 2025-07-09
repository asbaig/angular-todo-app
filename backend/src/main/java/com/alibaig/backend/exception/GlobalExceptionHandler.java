package com.alibaig.backend.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(TodoNotFoundException.class)
  public ResponseEntity<?> handleTodoNotFound(TodoNotFoundException ex) {
    Map<String, Object> errorBody = new HashMap<>();
    errorBody.put("timestamp", LocalDateTime.now());
    errorBody.put("status", HttpStatus.NOT_FOUND.value());
    errorBody.put("error", "Not Found");
    errorBody.put("message", ex.getMessage());

    return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> handleConstraintViolation(MethodArgumentNotValidException ex) {
    Map<String, Object> errorBody = new HashMap<>();
    errorBody.put("timestamp", LocalDateTime.now());
    errorBody.put("status", HttpStatus.BAD_REQUEST.value());
    errorBody.put("error", "Validation Failed");

    Map<String, String> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
        .collect(
            Collectors.toMap(fieldError -> fieldError.getField(), fieldError -> fieldError.getDefaultMessage(),
                (existing, replacement) -> existing));

    errorBody.put("fieldErrors", fieldErrors);

    return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UsernameAlreadyExistsException.class)
  public ResponseEntity<?> handleUsernameAlreadyExists(UsernameAlreadyExistsException ex) {
    Map<String, Object> errorBody = new HashMap<>();
    errorBody.put("timestamp", LocalDateTime.now());
    errorBody.put("status", HttpStatus.BAD_REQUEST.value());
    errorBody.put("error", "User Creation Failed");
    errorBody.put("message", ex.getMessage());

    return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<?> handleUserNotFound(UserNotFoundException ex) {
    Map<String, Object> errorBody = new HashMap<>();
    errorBody.put("timestamp", LocalDateTime.now());
    errorBody.put("status", HttpStatus.NOT_FOUND.value());
    errorBody.put("error", "Not Found");
    errorBody.put("message", ex.getMessage());

    return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(InvalidPasswordException.class)
  public ResponseEntity<?> handleInvalidPassword(InvalidPasswordException ex) {
    Map<String, Object> errorBody = new HashMap<>();
    errorBody.put("timestamp", LocalDateTime.now());
    errorBody.put("status", HttpStatus.UNAUTHORIZED.value());
    errorBody.put("error", "Validation Failed");
    errorBody.put("message", ex.getMessage());

    return new ResponseEntity<>(errorBody, HttpStatus.UNAUTHORIZED);
  }
}
