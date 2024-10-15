import {Injectable} from '@angular/core';
import Keycloak from "keycloak-js";
import {UserData} from "./UserData";

@Injectable({
  providedIn: 'root'
})
export class KeycloakService {
  _keycloak: Keycloak | undefined;
  userData: UserData | undefined;

  get keycloak() {
    if (!this._keycloak) {
      this._keycloak = new Keycloak({
        url: "http://localhost:9090",
        realm: "realm_yai",
        clientId: "example_of_client"
      });
      return this._keycloak;
    }
    return this._keycloak;
  }

  constructor() {
  }

  async init() {
    const authenticated = await this.keycloak?.init(
      {
        onLoad: "login-required"
      }
    );
    if (authenticated) {
      this.userData = (await this.keycloak?.loadUserProfile()) as UserData;
      this.userData.token = this.keycloak?.token;
      console.log(this.userData);
    }
  }

  login() {
    return this.keycloak?.login();
  }

  logout() {
    return this.keycloak?.logout();
  }

  handleDetails() {
    return this.keycloak?.accountManagement();
  }
}
