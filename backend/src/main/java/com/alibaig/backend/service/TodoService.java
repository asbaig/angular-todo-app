package com.alibaig.backend.service;

import java.util.List;

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
}
