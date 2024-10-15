import {HttpInterceptorFn} from '@angular/common/http';
import {inject} from "@angular/core";
import {KeycloakService} from "../keycloak/keycloak.service";

export const tokenInterceptor: HttpInterceptorFn =
  (req, next) => {
    const kcService: KeycloakService = inject(KeycloakService);
    const token = kcService.keycloak?.token;
    if (token) {
      const clonedRequest = req.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      });
      return next(clonedRequest);
    }
    return next(req);
  };
