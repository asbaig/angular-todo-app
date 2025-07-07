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

  toggleTodo(updatedTodo: Todo) {
    const id = updatedTodo.id!;
    this.todoService.toggleTodo(id, updatedTodo.completed).subscribe({
      next: () => {
        console.log(`todo with id ${id} toggled successfully`);

        this.todos = this.todos.map((todo) => {
          if (todo.id === id) {
            return {
              ...todo,
              completed: !todo.completed,
            };
          }
          return todo;
        });
      },
      error: (err) => {
        console.error('error toggling todo, should notify user on UI', err);
      },
    });
  }

  addTodo(newTodo: Todo) {
    this.todos = [...this.todos, newTodo];
  }

  deleteTodo(id: number) {
    this.todoService.deleteTodo(id).subscribe({
      next: () => {
        console.log(`todo with id ${id} deleted successfully`);

        this.todos = this.todos.filter((todo) => todo.id !== id);
      },
      error: (err) => {
        console.error('error deleting todo, should notify user on UI', err);
      },
    });
  }

  updateTitle(updatedTodo: Todo) {
    const id = updatedTodo.id!;

    this.todoService.updateTitle(id, updatedTodo.title).subscribe({
      next: () => {
        console.log(`todo with id ${id} updated successfully`);

        this.todos = this.todos.map((todo) => {
          if (todo.id === id) {
            return {
              ...todo,
              title: updatedTodo.title,
            };
          }
          return todo;
        });
      },
      error: (err) => {
        console.error('error updating todo, should notify user on UI', err);
      },
    });
  }
}
