import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { TodoItem } from './components/todo-item/todo-item';
import { Todo } from './models/todo';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, TodoItem],
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
