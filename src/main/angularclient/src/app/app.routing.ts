import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { JournalComponent } from "./journal/journal.component";
import {LoginComponent} from "./login/login.component";
import {SignupComponent} from "./signup/signup.component";
import {NgModule} from "@angular/core";
import {AuthGuardService} from "./service/auth.guard.service";

const appRoutes: Routes = [
  { path: 'login', component: LoginComponent},
  { path: 'signup', component: SignupComponent},
  { path: 'home', component: HomeComponent, canActivate: [AuthGuardService] },
  { path: 'journal', component: JournalComponent, canActivate: [AuthGuardService] },
  { path: '**', redirectTo: '', canActivate: [AuthGuardService] }
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
