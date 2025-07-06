import { Component, EventEmitter, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-todo-form',
  imports: [FormsModule],
  templateUrl: './todo-form.html',
  styleUrl: './todo-form.scss'
})
export class TodoForm {
  title = '';

  @Output() add = new EventEmitter<string>();

  onSubmit() {
    this.add.emit(this.title);
    this.title = '';
  }
}
