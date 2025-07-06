import { Component } from '@angular/core';
import { Todo } from '../../models/todo';
import { TodoItem } from '../todo-item/todo-item';
import { TodoForm } from '../todo-form/todo-form';

@Component({
  selector: 'app-todo-list',
  imports: [TodoItem, TodoForm],
  templateUrl: './todo-list.html',
  styleUrl: './todo-list.scss',
})
export class TodoList {
  todos: Todo[] = [
    {
      id: 1,
      title: 'Learn Angular',
      completed: false,
    },
    {
      id: 2,
      title: 'Learn React',
      completed: false,
    },
    {
      id: 3,
      title: 'Learn Vue',
      completed: false,
    },
  ];

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

  addTodo(title: string) {
    this.todos = [
      ...this.todos,
      {
        id: this.todos.length + 1,
        title,
        completed: false,
      },
    ];
  }
}
