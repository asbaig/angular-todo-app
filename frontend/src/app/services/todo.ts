import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Todo } from '../models/todo';
import { catchError, tap, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class TodoService {
  private apiUrl = 'http://localhost:8080/api/todos';
  private http = inject(HttpClient);

  getTodos() {
    return this.http.get<Todo[]>(this.apiUrl).pipe(
      catchError((err) => {
        console.error('Error fetching todos', err);
        return throwError(() => err);
      }),
    );
  }

  addTodo(todo: Todo) {
    return this.http.post<Todo>(this.apiUrl, todo).pipe(
      catchError((err) => {
        console.error('Error adding todo', err);
        return throwError(() => err);
      }),
    );
  }

  deleteTodo(id: number) {
    return this.http.delete(`${this.apiUrl}/${id}`).pipe(
      catchError((err) => {
        console.error('Error deleting todo', err);
        return throwError(() => err);
      }),
    );
  }

  toggleTodo(id: number, completed: boolean) {
    return this.http.patch<Todo>(`${this.apiUrl}/${id}`, { completed }).pipe(
      catchError((err) => {
        console.error('Error toggling todo', err);
        return throwError(() => err);
      }),
    );
  }

  updateTitle(id: number, title: string) {
    return this.http.patch<Todo>(`${this.apiUrl}/${id}`, { title }).pipe(
      catchError((err) => {
        console.error('Error updating todo', err);
        return throwError(() => err);
      }),
    );
  }
}
