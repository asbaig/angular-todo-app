import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { AuthService } from '../../services/auth-service';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-login-form',
  imports: [FormsModule, RouterLink],
  templateUrl: './login-form.html',
  styleUrl: './login-form.scss',
})
export class LoginForm {
  formData = {
    username: '',
    password: '',
  };

  message = '';
  showMessage = false;

  constructor(
    private authService: AuthService,
    private router: Router,
  ) {}

  onSubmit(form: NgForm) {
    if (form.invalid) {
      console.error('Form is invalid');

      this.message = 'Please fill out all the fields';
      this.showMessage = true;
      return;
    }

    this.authService
      .login(this.formData.username, this.formData.password)
      .subscribe({
        next: () => {
          this.router.navigate(['/todos']);
        },
        error: (err) => {
          console.error('Error logging in', err);
          this.message = 'Invalid username or password';
          this.showMessage = true;
        },
      });
  }
}
