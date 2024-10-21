import {APP_INITIALIZER, NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {LoginComponent} from './pages/login/login.component';
import {FormsModule} from "@angular/forms";
import {provideHttpClient, withInterceptors, withInterceptorsFromDi} from "@angular/common/http";
import {RegisterComponent} from './pages/register/register.component';
import {ActivateAccountComponent} from './pages/activate-account/activate-account.component';
import {CodeInputModule} from "angular-code-input";
import {PasswordRecoveryDemandComponent} from './pages/password-recovery-demand/password-recovery-demand.component';
import {CheckRecoveryComponent} from './pages/check-recovery/check-recovery.component';
import {SetNewPassComponent} from './pages/set-new-pass/set-new-pass.component';
import {MainPageComponent} from './pages/main-page/main-page.component';
import {tokenInterceptor} from "./services/interceptor/token.interceptor"
import {KeycloakService} from "./keycloak/keycloak.service";

export function kcFactory(kcService: KeycloakService) {
  return () => kcService.init();
}

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    ActivateAccountComponent,
    PasswordRecoveryDemandComponent,
    CheckRecoveryComponent,
    SetNewPassComponent,
    MainPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    CodeInputModule
  ],
  providers: [
    provideHttpClient
    (
      withInterceptorsFromDi(),
      withInterceptors([tokenInterceptor])
    ),
    {
      provide: APP_INITIALIZER,
      deps: [KeycloakService],
      useFactory: kcFactory,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
