import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent }  from './app.component';
import { routing }        from './app.routing';
import { HomeComponent } from './home/home.component';
import { JournalComponent } from "./journal/journal.component";

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    routing
  ],
  declarations: [
    AppComponent,
    HomeComponent,
    JournalComponent
  ],
  bootstrap: [AppComponent]
})

export class AppModule { }
