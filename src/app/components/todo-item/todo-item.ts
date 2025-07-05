import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Todo } from '../../models/todo';

@Component({
  selector: 'app-todo-item',
  imports: [],
  templateUrl: './todo-item.html',
  styleUrl: './todo-item.scss',
})
export class TodoItem {
  @Input() todo!: Todo;

  @Output() toggle = new EventEmitter<number>();

  onToggle() {
    console.log('clicked todo item', this.todo.id);
    this.toggle.emit(this.todo.id);
  }
}
