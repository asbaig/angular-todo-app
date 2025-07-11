import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { catchError, Observable, tap, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private url = 'http://localhost:8080/api/auth';
  private http = inject(HttpClient);

  login(username: string, password: string): Observable<any> {
    return this.http
      .post<any>(`${this.url}/login`, { username, password })
      .pipe(
        tap((res) => {
          if (res) {
            console.log("setting token");
            localStorage.setItem('token', res.token);
          }
        }),
        catchError((err) => {
          console.error('Error logging in', err);
          return throwError(() => err);
        }),
      );
  }

  register(username: string, password: string): Observable<any> {
    return this.http
      .post<any>(`${this.url}/register`, { username, password })
      .pipe(
        tap((res) => {
          if (res) {
            localStorage.setItem('token', res.token);
          }
        }),
        catchError((err) => {
          console.error('Error registering', err);
          return throwError(() => err);
        }),
      );
  }

  logout() {
    localStorage.removeItem('token');
  }

  getToken() {
    return localStorage.getItem('token');
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }
}
