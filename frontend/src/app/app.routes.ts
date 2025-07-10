import { Routes } from '@angular/router';
import { LoginForm } from './components/login-form/login-form';
import { TodoList } from './components/todo-list/todo-list';
import { authGuard } from './services/auth-guard';
import { redirectIfLoggedInGuard } from './services/redirect-if-logged-in-guard';

export const routes: Routes = [
  {
    path: 'login',
    component: LoginForm,
    canActivate: [redirectIfLoggedInGuard],
  },
  { path: 'todos', component: TodoList, canActivate: [authGuard] },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
];
