package com.alibaig.backend.exception;

public class TodoNotFoundException extends RuntimeException {

  public TodoNotFoundException(String message) {
    super(message);
  }

}
