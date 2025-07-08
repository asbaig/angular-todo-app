package com.alibaig.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.alibaig.backend.dto.TodoDTO;
import com.alibaig.backend.model.Todo;
import com.alibaig.backend.repository.TodoRepository;

@Service
public class TodoService {

  private final TodoRepository todoRepository;

  public TodoService(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  public List<TodoDTO> getTodos() {
    return todoRepository.findAll().stream().map(this::toDto).toList();
  }

  public TodoDTO createTodo(TodoDTO todoDto) {
    Todo todo = toEntity(todoDto);
    TodoDTO savedTodoDto = toDto(todoRepository.save(todo));

    return savedTodoDto;
  }

  public Optional<TodoDTO> updateTodo(Long id, TodoDTO todoDto) {
    return todoRepository.findById(id).map(existingTodo -> {
      existingTodo.setTitle(todoDto.getTitle());
      existingTodo.setCompleted(todoDto.isCompleted());

      TodoDTO updatedTodoDto = toDto(todoRepository.save(existingTodo));

      return updatedTodoDto;
    });
  }

  public TodoDTO toDto(Todo todo) {
    return new TodoDTO(todo.getId(), todo.getTitle(), todo.isCompleted());
  }

  public Todo toEntity(TodoDTO dto) {
    Todo todo = new Todo();
    todo.setId(dto.getId());
    todo.setTitle(dto.getTitle());
    todo.setCompleted(dto.isCompleted());

    return todo;
  }
}
