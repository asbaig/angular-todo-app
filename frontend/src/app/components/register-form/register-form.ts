import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth-service';

@Component({
  selector: 'app-register-form',
  imports: [FormsModule, RouterLink],
  templateUrl: './register-form.html',
  styleUrl: './register-form.scss',
})
export class RegisterForm {
  formData = {
    username: '',
    password: '',
  };

  constructor(
    private authService: AuthService,
    private router: Router,
  ) {}

  onSubmit() {
    this.authService.register(this.formData.username, this.formData.password).subscribe({
      next: () => {
        this.router.navigate(['/todos']);
      },
      error: (err) => {
        console.error('Error registering', err);
      },
    });
  }
}
