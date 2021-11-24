import {Component, OnInit} from '@angular/core';
import {AuthService} from "./service/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {
  constructor(private authService: AuthService,
              private router: Router){}

  ngOnInit(): void {
    if(this.authService.isLoggedIn()) {
      this.authService.refreshTokenForApp();
    }
    setInterval(() => {
      if (this.authService.isLoggedIn()) {
        this.authService.refreshTokenForApp();
      }
    }, 50000);
  }

  logout() {
    this.authService.logout();
    this.router.navigateByUrl('/login');
  }

  isLoggedIn() {
    return this.authService.isLoggedIn();
  }

  getRoute(){
    return this.router.url;
  }
}
