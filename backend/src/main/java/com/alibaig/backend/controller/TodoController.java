package com.alibaig.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaig.backend.dto.TodoDTO;
import com.alibaig.backend.service.TodoService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

  private final TodoService todoService;

  public TodoController(TodoService todoService) {
    this.todoService = todoService;
  }

  @GetMapping()
  public ResponseEntity<List<TodoDTO>> getTodos() {
    return ResponseEntity.ok(todoService.getTodos());
  }

  @GetMapping("/{id}")
  public ResponseEntity<TodoDTO> getTodo(@PathVariable Long id) {
    TodoDTO todo = todoService.getTodo(id);

    return ResponseEntity.ok(todo);

  }

  @PostMapping()
  public ResponseEntity<TodoDTO> createTodo(@Valid @RequestBody TodoDTO todoDto) {
    TodoDTO savedTodo = todoService.createTodo(todoDto);

    return ResponseEntity.status(201).body(savedTodo);
  }

  @PutMapping("/{id}")
  public ResponseEntity<TodoDTO> updateTodo(@PathVariable Long id, @RequestBody TodoDTO todoDto) {
    TodoDTO updatedTodo = todoService.updateTodo(id, todoDto);

    return ResponseEntity.ok(updatedTodo);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
    todoService.deleteTodo(id);

    return ResponseEntity.noContent().build();
  }
}
