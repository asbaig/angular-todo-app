package com.alibaig.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.alibaig.backend.model.Todo;
import com.alibaig.backend.repository.TodoRepository;

@Service
public class TodoService {

  private final TodoRepository todoRepository;

  public TodoService(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  public List<Todo> getTodos() {
    return todoRepository.findAll();
  }

  public Todo createTodo(Todo todo) {
    return todoRepository.save(todo);
  }

  public Optional<Todo> updateTodo(Long id, Todo todo) {
    return todoRepository.findById(id).map(existingTodo -> {
      existingTodo.setTitle(todo.getTitle());
      existingTodo.setCompleted(todo.isCompleted());
      return todoRepository.save(existingTodo);
    });
  }
}
