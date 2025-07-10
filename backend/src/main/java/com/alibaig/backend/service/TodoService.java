package com.alibaig.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaig.backend.dto.TodoDTO;
import com.alibaig.backend.dto.TodoPatchDTO;
import com.alibaig.backend.exception.TodoNotFoundException;
import com.alibaig.backend.model.Todo;
import com.alibaig.backend.model.User;
import com.alibaig.backend.repository.TodoRepository;
import com.alibaig.backend.util.UserUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {

  private final TodoRepository todoRepository;

  public List<TodoDTO> getTodos() {
    User currentUser = UserUtils.getCurrentUser();

    List<Todo> todos = todoRepository.findByUser(currentUser);

    return todos.stream().map(this::toDto).toList();
  }

  public TodoDTO getTodo(Long id) {
    User currentUser = UserUtils.getCurrentUser();

    Todo todo = todoRepository.findByIdAndUser(id, currentUser)
        .orElseThrow(() -> new TodoNotFoundException(
            "Todo for user '" + currentUser.getUsername() + "' not found with id: " + id));

    return toDto(todo);
  }

  public TodoDTO createTodo(TodoDTO todoDto) {
    User currentUser = UserUtils.getCurrentUser();

    Todo todo = toEntity(todoDto);

    todo.setUser(currentUser);

    return toDto(todoRepository.save(todo));
  }

  public TodoDTO updateTodo(Long id, TodoDTO todoDto) {
    User currentUser = UserUtils.getCurrentUser();

    Todo existingTodo = todoRepository.findByIdAndUser(id, currentUser)
        .orElseThrow(() -> new TodoNotFoundException(
            "Todo for user '" + currentUser.getUsername() + "' not found with id: " + id));

    existingTodo.setTitle(todoDto.getTitle());
    existingTodo.setCompleted(todoDto.getCompleted());

    return toDto(todoRepository.save(existingTodo));
  }

  public void deleteTodo(Long id) {
    User currentUser = UserUtils.getCurrentUser();

    Todo existingTodo = todoRepository.findByIdAndUser(id, currentUser)
        .orElseThrow(() -> new TodoNotFoundException(
            "Todo for user '" + currentUser.getUsername() + "' not found with id: " + id));

    todoRepository.delete(existingTodo);
  }

  public TodoDTO patchTodo(Long id, TodoPatchDTO todoDto) {
    User currentUser = UserUtils.getCurrentUser();

    Todo existingTodo = todoRepository.findByIdAndUser(id, currentUser)
        .orElseThrow(() -> new TodoNotFoundException(
            "Todo for user '" + currentUser.getUsername() + "' not found with id: " + id));

    if (todoDto.getTitle() != null) {
      existingTodo.setTitle(todoDto.getTitle());
    }

    if (todoDto.getCompleted() != null) {
      existingTodo.setCompleted(todoDto.getCompleted());
    }

    todoRepository.save(existingTodo);

    return toDto(existingTodo);
  }

  public TodoDTO toDto(Todo todo) {
    return new TodoDTO(todo.getId(), todo.getTitle(), todo.getCompleted());
  }

  public Todo toEntity(TodoDTO dto) {
    Todo todo = new Todo();
    todo.setId(dto.getId());
    todo.setTitle(dto.getTitle());
    todo.setCompleted(dto.getCompleted());

    return todo;
  }
}
