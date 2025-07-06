import { Component, inject, OnInit } from '@angular/core';
import { Todo } from '../../models/todo';
import { TodoItem } from '../todo-item/todo-item';
import { TodoForm } from '../todo-form/todo-form';
import { TodoService } from '../../services/todo';

@Component({
  selector: 'app-todo-list',
  imports: [TodoItem, TodoForm],
  templateUrl: './todo-list.html',
  styleUrl: './todo-list.scss',
})
export class TodoList implements OnInit {
  todos: Todo[] = [];

  private todoService = inject(TodoService);

  ngOnInit(): void {
    this.todoService.getTodos().subscribe({
      next: (todos) => {
        this.todos = todos;
      },
      error: (err) => {
        console.log('error getting todos, should notify user on UI', err);
      },
    });
  }

  toggleTodo(id: number) {
    console.log('message received', id);

    this.todos = this.todos.map((todo) => {
      if (todo.id === id) {
        return {
          ...todo,
          completed: !todo.completed,
        };
      }

      return todo;
    });
  }

  addTodo(newTodo: Todo) {
    this.todos = [...this.todos, newTodo];
  }

  deleteTodo(id: number) {
    this.todos = this.todos.filter((todo) => todo.id !== id);

    this.todoService.deleteTodo(id).subscribe({
      next: () => {
        console.log('todo deleted');
      },
      error: (err) => {
        console.error('error deleting todo, should notify user on UI', err);
      },
    });
  }
}
