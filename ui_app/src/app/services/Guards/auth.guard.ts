import {CanActivateFn, Router} from '@angular/router';
import {inject} from "@angular/core";
import {KeycloakService} from "../keycloak/keycloak.service";

export const authGuard: CanActivateFn =
  (route, state) => {
    const kcService: KeycloakService = inject(KeycloakService);
    const router = inject(Router);
    if (kcService.keycloak?.isTokenExpired()) {
      router.navigate(['']);
      return false;
    }
    return true;
  };
