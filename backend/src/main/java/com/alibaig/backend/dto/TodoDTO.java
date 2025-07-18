package com.alibaig.backend.dto;

import jakarta.validation.constraints.NotBlank;

public class TodoDTO {
  private Long id;

  @NotBlank
  private String title;

  private Boolean completed;

  public TodoDTO(Long id, String title, Boolean completed) {
    this.id = id;
    this.title = title;
    this.completed = completed;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Boolean getCompleted() {
    return completed;
  }

  public void setCompleted(Boolean completed) {
    this.completed = completed;
  }
}
