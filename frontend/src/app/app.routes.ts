import { Routes } from '@angular/router';
import { LoginForm } from './components/login-form/login-form';
import { TodoList } from './components/todo-list/todo-list';
import { authGuard } from './services/auth-guard';
import { redirectIfLoggedInGuard } from './services/redirect-if-logged-in-guard';
import { RegisterForm } from './components/register-form/register-form';

export const routes: Routes = [
  {
    path: 'login',
    component: LoginForm,
    canActivate: [redirectIfLoggedInGuard],
  },
  { path: 'todos', component: TodoList, canActivate: [authGuard] },
  {
    path: 'register',
    component: RegisterForm,
    canActivate: [redirectIfLoggedInGuard],
  },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
];
