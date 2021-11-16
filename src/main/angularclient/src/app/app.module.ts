import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent }  from './app.component';
import { routing }        from './app.routing';
import { HomeComponent } from './home/home.component';
import { JournalComponent } from "./journal/journal.component";
import {LoginComponent} from "./login/login.component";

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    routing,
    ReactiveFormsModule
  ],
  declarations: [
    AppComponent,
    HomeComponent,
    JournalComponent,
    LoginComponent
  ],
  bootstrap: [AppComponent]
})

export class AppModule { }
