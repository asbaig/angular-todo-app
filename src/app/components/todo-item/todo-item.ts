import { Component, EventEmitter, inject, Input, Output } from '@angular/core';
import { Todo } from '../../models/todo';
import { TodoService } from '../../services/todo';

@Component({
  selector: 'app-todo-item',
  imports: [],
  templateUrl: './todo-item.html',
  styleUrl: './todo-item.scss',
})
export class TodoItem {
  @Input() todo!: Todo;

  @Output() toggle = new EventEmitter<Todo>();
  @Output() delete = new EventEmitter<number>();

  onToggle() {
    console.log('clicked toggle on todo with id', this.todo.id);
    this.toggle.emit({ ...this.todo, completed: !this.todo.completed });
  }

  onDelete(event: MouseEvent) {
    event.stopPropagation();
    console.log('clicked delete on todo with id', this.todo.id);
    this.delete.emit(this.todo.id);
  }
}
