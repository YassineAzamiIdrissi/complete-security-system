import {Component} from '@angular/core';
import {AuthService} from "../../services/services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-check-recovery',
  templateUrl: './check-recovery.component.html',
  styleUrl: './check-recovery.component.scss'
})
export class CheckRecoveryComponent {

  errorMessage: string = "";
  submitted: boolean = false;

  constructor(private authService: AuthService, private router: Router) {
  }

  checkCode(code: string
  ) {
    this.submitted = true;
    this.authService.checkRecovery({
      recovery: code
    }).subscribe({
      next: (resp) => {
        console.log("Code is correct");
        // going to the new password page...
      },
      error: (err) => {
        this.errorMessage = err.error.message;
      }
    })
  }

}
