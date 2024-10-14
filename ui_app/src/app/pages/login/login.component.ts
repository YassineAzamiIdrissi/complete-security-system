import {Component} from '@angular/core';
import {AuthService} from "../../services/services/auth.service";
import {Router} from "@angular/router";
import {AuthenticationRequest} from "../../services/models/authentication-request";
import {TokenService} from "../../services/token/token.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  request: AuthenticationRequest = {
    password: "",
    email: ""
  }

  errors: Array<String> = [];
  message: string = "";

  constructor(private authService: AuthService,
              private router: Router,
              private tokenService: TokenService) {
  }

  login() {
    this.errors = [];
    this.message = "";
    this.authService.authenticate({
      "body": this.request
    }).subscribe(
      {
        next: (resp) => {
          this.tokenService.token = resp.accessToken as string;

        },
        error: (err) => {
          console.log(err);
          if (err.error.errors) {
            this.errors = err.error.errors;
          } else {
            this.message = err.error.message;
          }
          setTimeout(() => {
            this.errors = [];
            this.message = "";
          }, 3000);
        }
      })
  }

  moveToRegister() {
    this.router.navigate(["register"]);
  }

  startRecovery() {
    this.router.navigate(["demand-recovery"]);
  }
}
