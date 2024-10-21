import {Injectable} from '@angular/core';
import Keycloak from "keycloak-js";

@Injectable({
  providedIn: 'root'
})
export class KeycloakService {
  _keycloak: Keycloak | undefined;
  get keycloak() {
    if (!this._keycloak) {
      this._keycloak = new Keycloak({
        url: "http://localhost:9090",
        realm: "mock_realm",
        clientId: "cl_id"
      })
    }
    return this._keycloak;
  }

  constructor() {
  }

  async init() {
    const authenticated = await this.keycloak?.init({
      onLoad: "login-required"
    })
    if (authenticated) {
      console.log("CONNECTED");
    }
  }

  login() {
    return this.keycloak.login();
  }

  logout() {
    return this.keycloak.logout();
  }
}
