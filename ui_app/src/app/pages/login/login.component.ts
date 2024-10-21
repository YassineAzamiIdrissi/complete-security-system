import {Component, OnInit} from '@angular/core';
import {KeycloakService} from "../../keycloak/keycloak.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent implements OnInit {
  constructor(private kcService: KeycloakService) {
  }

  async ngOnInit(): Promise<void> {
    await this.kcService.init();
    await this.kcService.login();
  }
}
