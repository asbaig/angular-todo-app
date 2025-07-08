package com.alibaig.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaig.backend.dto.TodoDTO;
import com.alibaig.backend.exception.TodoNotFoundException;
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

  public TodoDTO getTodo(Long id) {
    Todo todo = todoRepository.findById(id)
        .orElseThrow(() -> new TodoNotFoundException("Todo not found with id: " + id));

    return toDto(todo);
  }

  public TodoDTO createTodo(TodoDTO todoDto) {
    Todo todo = toEntity(todoDto);
    TodoDTO savedTodoDto = toDto(todoRepository.save(todo));

    return savedTodoDto;
  }

  public TodoDTO updateTodo(Long id, TodoDTO todoDto) {
    Todo existingTodo = todoRepository.findById(id)
        .orElseThrow(() -> new TodoNotFoundException("Todo not found with id: " + id));

    existingTodo.setTitle(todoDto.getTitle());
    existingTodo.setCompleted(todoDto.isCompleted());

    Todo updatedTodo = todoRepository.save(existingTodo);
    return toDto(updatedTodo);
  }

  public void deleteTodo(Long id) {
    Todo existingTodo = todoRepository.findById(id)
        .orElseThrow(() -> new TodoNotFoundException("Todo not found with id: " + id));

    todoRepository.delete(existingTodo);
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
