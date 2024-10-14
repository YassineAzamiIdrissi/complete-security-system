import {Component, OnInit} from '@angular/core';
import {NewPasswordCouple} from "../../services/models/new-password-couple";
import {AuthService} from "../../services/services/auth.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-set-new-pass',
  templateUrl: './set-new-pass.component.html',
  styleUrl: './set-new-pass.component.scss'
})
export class SetNewPassComponent implements OnInit {
  code: string = "";
  errors: Array<string> = [];
  actionMessage: string = "";
  witness: boolean = false;

  constructor(private authService: AuthService,
              private router: Router,
              private activatedRoute: ActivatedRoute) {
  }

  ngOnInit() {
    this.code = this.activatedRoute.snapshot.params['code'];
  }

  passwordsSet: NewPasswordCouple = {
    newPassword: "",
    confirmNewPassword: ""
  }

  setNewPassword() {
    this.authService.setNewPassword({
      code: this.code,
      body: this.passwordsSet
    }).subscribe({
      next: (resp) => {
        this.actionMessage = "Password successfully changed, you can ";
        this.witness = true;
      },
      error: (err) => {
        console.log(err);
        if (err.error?.errors) {
          this.errors = err.error.errors;
        } else {
          this.actionMessage = err.error.message;
        }
        this.witness = false;
        setTimeout(() => {
          this.errors = [];
          this.actionMessage = "";
        }, 2400);
      }
    })
  }

  goToLogin() {
    this.router.navigate(['login']);
  }
}
