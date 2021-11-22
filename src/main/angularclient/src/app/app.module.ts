import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {AppComponent} from './app.component';
import {AppRoutingModule} from './app.routing';
import {HomeComponent} from './home/home.component';
import {JournalComponent} from "./journal/journal.component";
import {LoginComponent} from "./login/login.component";
import {SignupComponent} from "./signup/signup.component";
import {UserListComponent} from "./user-list/user-list.component";
import {NgxWebstorageModule} from "ngx-webstorage";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {MatSnackBarModule} from "@angular/material/snack-bar";
import {TokenInterceptor} from "./token-interceptor";

import {HighlightDirective} from './directives/highlight.directive';
import {FilterPipe} from "./pipes/filter.pipe";
import {DateFilterPipe} from "./pipes/dateFilter.pipe";

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    NgxWebstorageModule.forRoot(),
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    FontAwesomeModule,
    NgbModule,
    MatSnackBarModule
  ],
  declarations: [
    AppComponent,
    HomeComponent,
    JournalComponent,
    LoginComponent,
    SignupComponent,
    UserListComponent,
    HighlightDirective,
    FilterPipe,
    DateFilterPipe
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent],
})

export class AppModule {
}
