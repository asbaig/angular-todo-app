import { Component, EventEmitter, inject, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Todo } from '../../models/todo';
import { TodoService } from '../../services/todo';

@Component({
  selector: 'app-todo-form',
  imports: [FormsModule],
  templateUrl: './todo-form.html',
  styleUrl: './todo-form.scss',
})
export class TodoForm {
  title = '';

  @Output() add = new EventEmitter<Todo>();

  private todoService = inject(TodoService);

  onSubmit() {
    const newTodo: Todo = {
      title: this.title,
      completed: false,
    };

    this.todoService.addTodo(newTodo).subscribe({
      next: (createdTodo) => {
        this.add.emit(createdTodo);
        this.title = '';
      },
      error: (err) => {
        console.error('error adding todo, should notify user on UI', err);
      },
    });
  }
}
