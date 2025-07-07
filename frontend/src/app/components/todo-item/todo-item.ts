import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Todo } from '../../models/todo';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-todo-item',
  imports: [FormsModule],
  templateUrl: './todo-item.html',
  styleUrl: './todo-item.scss',
})
export class TodoItem {
  @Input() todo!: Todo;

  @Output() toggle = new EventEmitter<Todo>();
  @Output() delete = new EventEmitter<number>();
  @Output() editTitle = new EventEmitter<Todo>();

  editing = false;
  newTitle = '';

  onToggle() {
    if (this.editing) {
      return;
    }

    console.log('clicked toggle on todo with id', this.todo.id);
    this.toggle.emit({ ...this.todo, completed: !this.todo.completed });
  }

  onDelete(event: MouseEvent) {
    event.stopPropagation();
    console.log('clicked delete on todo with id', this.todo.id);
    this.delete.emit(this.todo.id);
  }

  onEdit(event: MouseEvent) {
    event.stopPropagation();
    console.log('clicked edit on todo with id', this.todo.id);

    this.editing = true;
  }

  onSave(event: Event) {
    event.stopPropagation();
    console.log('clicked save on todo with id', this.todo.id);
    this.editTitle.emit({ ...this.todo, title: this.newTitle });
    this.editing = false;
  }
}
