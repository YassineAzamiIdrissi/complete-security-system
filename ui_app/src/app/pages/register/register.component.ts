import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from "../../services/services/auth.service";
import {RegistrationRequest} from "../../services/models/registration-request";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {
  registerRequest: RegistrationRequest = {
    firstname: "",
    lastname: "",
    email: "",
    password: ""
  }

  errors: Array<string> = [];
  errorMessage: string = "";

  constructor(private router: Router,
              private authService: AuthService) {
  }

  register() {
    this.errors = [];
    this.errorMessage = "";
    this.authService.register({
      'body': this.registerRequest
    }).subscribe({
      next: (resp) => {
        // going to the account activation page...
        this.router.navigate(['login']);
      },
      error: (err) => {
        if (err.error.errors) {
          this.errors = err.error.errors;
        } else {
          this.errorMessage = err.error.message;
        }
        setTimeout(() => {
          this.errors = [];
          this.errorMessage = "";
        }, 2500);
      }
    })
  }

  moveToLogin() {
    this.router.navigate(['login']);
  }


}
