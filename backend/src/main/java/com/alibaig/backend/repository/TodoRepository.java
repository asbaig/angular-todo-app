package com.alibaig.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alibaig.backend.model.Todo;
import com.alibaig.backend.model.User;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

  public List<Todo> findByUser(User user);
}
