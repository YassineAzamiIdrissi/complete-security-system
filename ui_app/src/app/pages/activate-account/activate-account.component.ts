import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {AuthService} from "../../services/services/auth.service";

@Component({
  selector: 'app-activate-account',
  templateUrl: './activate-account.component.html',
  styleUrl: './activate-account.component.scss'
})
export class ActivateAccountComponent implements OnInit {
  email: string = "";
  message: string = "";
  witness: boolean = false;
  submitted: boolean = false;

  constructor(private activatedRoute: ActivatedRoute,
              private authService: AuthService,
              private router: Router) {
  }

  ngOnInit() {
    this.email = this.activatedRoute.snapshot.queryParams['email'];
  }

  checkActivationCode(code: string) {
    this.submitted = true;
    this.authService.activateAccount({
      activation_code: code
    }).subscribe({
      next: (resp) => {
        this.message = "Activated successfully, you can ";
        this.witness = true;
      },
      error: (err) => {
        this.message = err.error.message;
        this.witness = false;
        console.log(err);
      }
    })
  }

  moveToLogin() {
    this.router.navigate(["login"]);
  }
}
