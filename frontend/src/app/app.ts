import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Todo } from './models/todo';
import { TodoList } from './components/todo-list/todo-list';
import { LoginForm } from './components/login-form/login-form';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, TodoList, LoginForm],
  templateUrl: './app.html',
  styleUrl: './app.scss',
})
export class App {
  todo: Todo = {
    id: 1,
    title: 'Learn Angular',
    completed: false
  }
}
