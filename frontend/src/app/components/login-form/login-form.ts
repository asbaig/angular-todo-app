import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-form',
  imports: [FormsModule],
  templateUrl: './login-form.html',
  styleUrl: './login-form.scss',
})
export class LoginForm {
  username: string = '';
  password: string = '';

  constructor(
    private authService: AuthService,
    private router: Router,
  ) {}

  onSubmit() {
    console.log('submit', this.username, this.password);

    this.authService.login(this.username, this.password).subscribe({
      next: () => {
        this.router.navigate(['/todos']);
      },
      error: (err) => {
        console.error('Error logging in', err);
      },
    });
  }
}
