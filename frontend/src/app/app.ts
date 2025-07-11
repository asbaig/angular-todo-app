import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Todo } from './models/todo';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.scss',
})
export class App {
  todo: Todo = {
    id: 1,
    title: 'Learn Angular',
    completed: false,
  };
}
