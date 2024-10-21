import {CanActivateFn, Router} from '@angular/router';
import {inject} from "@angular/core";
import {KeycloakService} from "../../keycloak/keycloak.service";

export const authGuard: CanActivateFn =
  (route, state) => {
    // const tokenService = inject(TokenService);
    const router = inject(Router);
    const kcService = inject(KeycloakService);
    if (kcService.keycloak.isTokenExpired()) {
      router.navigate(['login']);
      return false;
    }
    return true;
  };
