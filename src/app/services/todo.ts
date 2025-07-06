import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Todo } from '../models/todo';
import { catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TodoService {
  private apiUrl = 'http://localhost:3000/todos';
  private http = inject(HttpClient);

  getTodos() {
    return this.http.get<Todo[]>(this.apiUrl).pipe(
      catchError((err) => {
        console.error("Error fetching todos", err);
        return throwError(() => err);
      })
    );
  }

  addTodo(todo: Todo) {
    return this.http.post<Todo>(this.apiUrl, todo).pipe(
      catchError((err) => {
        console.error("Error adding todo", err);
        return throwError(() => err);
      })
    )
  }
}
