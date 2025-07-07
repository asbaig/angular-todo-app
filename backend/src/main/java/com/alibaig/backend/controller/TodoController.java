package com.alibaig.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaig.backend.model.Todo;
import com.alibaig.backend.service.TodoService;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

  private final TodoService todoService;

  public TodoController(TodoService todoService) {
    this.todoService = todoService;
  }

  @GetMapping()
  public ResponseEntity<List<Todo>> getTodos() {
    return ResponseEntity.ok(todoService.getTodos());
  }

  @PostMapping()
  public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
    Todo savedTodo = todoService.createTodo(todo);

    return ResponseEntity.status(201).body(savedTodo);
  }
}
