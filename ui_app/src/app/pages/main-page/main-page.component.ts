import {Component, OnInit} from '@angular/core';
import {CarService} from "../../services/services/car.service";
import {CarResponse} from "../../services/models/car-response";
import {TokenService} from "../../services/token/token.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrl: './main-page.component.scss'
})
export class MainPageComponent implements OnInit {
  data: Array<CarResponse> = [];
  username: string = "";

  constructor(private carService: CarService,
              private tokenService: TokenService,
              private router: Router) {
  }

  ngOnInit() {
    const details = this.tokenService.decodePayLoad();
    this.username = details?.fullName;
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
    localStorage.removeItem("token");
    this.router.navigate(["login"]);
  }
}
