import {Routes, RouterModule} from '@angular/router';
import {JournalComponent} from "./journal/journal.component";
import {LoginComponent} from "./login/login.component";
import {SignupComponent} from "./signup/signup.component";
import {NgModule} from "@angular/core";
import {AuthGuardService} from "./service/auth.guard.service";
import {AboutComponent} from "./about/about.component";

const appRoutes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'signup', component: SignupComponent},
  {path: 'about', component: AboutComponent},
  {path: 'journal', component: JournalComponent, canActivate: [AuthGuardService]},
  {path: '**', redirectTo: '/journal', canActivate: [AuthGuardService]}
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
