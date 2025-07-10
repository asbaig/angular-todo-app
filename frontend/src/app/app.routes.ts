import { Routes } from '@angular/router';
import { LoginForm } from './components/login-form/login-form';
import { TodoList } from './components/todo-list/todo-list';

export const routes: Routes = [
  { path: 'login', component: LoginForm},
  { path: 'todos', component: TodoList},
  { path: '', redirectTo: '/login', pathMatch: 'full' }
];
