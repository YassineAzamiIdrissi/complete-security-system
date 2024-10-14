import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from "../../services/services/auth.service";
import {EmailRequest} from "../../services/models/email-request";

@Component({
  selector: 'app-password-recovery-demand',
  templateUrl: './password-recovery-demand.component.html',
  styleUrl: './password-recovery-demand.component.scss'
})
export class PasswordRecoveryDemandComponent {
  emailRequest: EmailRequest = {
    email: ""
  };
  errorMessage: string = "";
  errors: Array<String> = [];

  constructor(private router: Router, private authService: AuthService) {
  }


  checkEmail(): void {
    this.errorMessage = "";
    this.errors = [];
    this.authService.demandRecovery({
      "body": this.emailRequest,
    }).subscribe({
      next: (resp) => {
        // navigating to the suitable page..
        this.router.navigate(['check-recovery'], {
          queryParams: {
            "email": this.emailRequest.email
          }
        })
      },
      error: (err) => {
        console.log(err);
        if (err.error.errors?.length) {
          this.errors = err.error.errors;
        } else {
          this.errorMessage = err.error.message;
        }
        setTimeout(() => {
          this.errorMessage = "";
          this.errors = [];
        }, 2400)
      }
    })
  }
}
