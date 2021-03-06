import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {AppComponent} from './app.component';
import {AppRoutingModule} from './app.routing';
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
import {CommonModule} from "@angular/common";
import {MatIconModule} from "@angular/material/icon";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {AboutComponent} from "./about/about.component";
import {DragDropModule} from "@angular/cdk/drag-drop";

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
    MatSnackBarModule,
    CommonModule,
    MatIconModule,
    MatFormFieldModule,
    MatDatepickerModule,
    DragDropModule
  ],
  declarations: [
    AppComponent,
    JournalComponent,
    AboutComponent,
    LoginComponent,
    SignupComponent,
    UserListComponent,
    HighlightDirective,
    FilterPipe
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
