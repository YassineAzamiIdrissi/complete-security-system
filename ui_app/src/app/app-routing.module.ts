import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./pages/login/login.component";
import {RegisterComponent} from "./pages/register/register.component";
import {ActivateAccountComponent} from "./pages/activate-account/activate-account.component";
import {PasswordRecoveryDemandComponent} from "./pages/password-recovery-demand/password-recovery-demand.component";
import {CheckRecoveryComponent} from "./pages/check-recovery/check-recovery.component";
import {SetNewPassComponent} from "./pages/set-new-pass/set-new-pass.component";
import {MainPageComponent} from "./pages/main-page/main-page.component";
import {authGuard} from "./services/Guards/auth.guard";

const routes: Routes = [
  {
    path: "",
    redirectTo: "login",
    pathMatch: "full"
  },
  {
    path: "login",
    component: LoginComponent
  },
  {
    path: "register",
    component: RegisterComponent
  },
  {
    path: "activate-account",
    component: ActivateAccountComponent
  },
  {
    path: "demand-recovery",
    component: PasswordRecoveryDemandComponent
  },
  {
    path: "check-recovery",
    component: CheckRecoveryComponent
  },
  {
    path: "set-new-pass/:code",
    component: SetNewPassComponent
  },
  {
    path: "main",
    component: MainPageComponent,
    canActivate: [authGuard]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
