import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { JournalComponent } from "./journal/journal.component";
import {LoginComponent} from "./login/login.component";

const appRoutes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent},
  { path: 'journal', component: JournalComponent},
  { path: '**', redirectTo: '' }
];

export const routing = RouterModule.forRoot(appRoutes);
