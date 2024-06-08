import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { ValidatorsService } from '../../../business/services/validators.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.css'
})
export class LoginPageComponent {

  private fb = inject( FormBuilder );
  private authService = inject(AuthService);
  private validatorsService = inject(ValidatorsService);
  private router = inject(Router);

// Validators.pattern(this.validatorsService.emailPattern)
  public myForm: FormGroup = this.fb.group({
    username: ['', [ Validators.required,]],
    password: ['', [ Validators.required, Validators.minLength((6)) ]],
  });


  login() {
    const { username, password } = this.myForm.value;

    this.authService.login(username, password)
    .subscribe({
      next: () => this.router.navigateByUrl('/dashboard/home'),
      error: (message) => {
        console.log(message);
      }


    })

  }

}
