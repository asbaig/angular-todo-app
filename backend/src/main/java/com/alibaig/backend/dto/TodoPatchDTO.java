package com.alibaig.backend.dto;

public class TodoPatchDTO {
  private String title;

  private Boolean completed;

  public TodoPatchDTO(String title, Boolean completed) {
    this.title = title;
    this.completed = completed;
  }

  public TodoPatchDTO() {
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
