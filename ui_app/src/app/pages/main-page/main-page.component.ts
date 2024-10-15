import {Component, OnInit} from '@angular/core';
import {CarService} from "../../services/services/car.service";
import {CarResponse} from "../../services/models/car-response";
import {TokenService} from "../../services/token/token.service";
import {Router} from "@angular/router";
import {KeycloakService} from "../../services/keycloak/keycloak.service";
import Keycloak from "keycloak-js";

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrl: './main-page.component.scss'
})
export class MainPageComponent implements OnInit {
  data: Array<CarResponse> = [];
  username: string = "";

  constructor(private carService: CarService,
              private kcService: KeycloakService,
              private router: Router) {
  }

  ngOnInit() {
    const details = (this.kcService.keycloak as Keycloak).tokenParsed;
    if (details && details["name"]) {
      this.username = details["name"];
    }
    this.fetchCars();
  }

  fetchCars() {
    this.carService.getAllCars().subscribe({
      next: (resp) => {
        this.data = resp;
      },
      error: (err) => {

      }
    })
  }

  logout() {
    this.kcService.keycloak?.logout();
  }
}
