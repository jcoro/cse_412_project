import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SignupRequest } from '../signup/signup';
import { Observable } from 'rxjs';
import { LocalStorageService } from 'ngx-webstorage';
import {LoginRequest, LoginResponse, RefreshTokenRequest} from '../login/login';
import { map, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient,
              private localStorage: LocalStorageService) {
  }

  signup(signupRequest: SignupRequest): Observable<any> {
    return this.http.post('http://localhost:8082/api/auth/signup', signupRequest, { responseType: 'text' });
  }

  login(loginRequest: LoginRequest): Observable<boolean> {
    return this.http.post<LoginResponse>('http://localhost:8082/api/auth/login',
      loginRequest).pipe(map(data => {
      this.localStorage.store('authenticationToken', data.authenticationToken);
      this.localStorage.store('username', data.username);
      this.localStorage.store('refreshToken', data.refreshToken);
      this.localStorage.store('expiresAt', data.expiresAt);
      return true;
    }));
  }

  getToken() {
    return this.localStorage.retrieve('authenticationToken');
  }

  refreshToken() {
    return this.http.post<LoginResponse>('http://localhost:8082/api/auth/refresh/token',
      this.refreshTokenPayload())
      .pipe(tap(response => {
        this.localStorage.clear('authenticationToken');
        this.localStorage.clear('expiresAt');

        this.localStorage.store('authenticationToken',
          response.authenticationToken);
        this.localStorage.store('expiresAt', response.expiresAt);
      }));
  }

  refreshTokenForApp() {
    return this.http.post<LoginResponse>('http://localhost:8082/api/auth/refresh/token',
      this.refreshTokenPayload())
      .subscribe(response => {
        this.localStorage.clear('authenticationToken');
        this.localStorage.clear('expiresAt');

        this.localStorage.store('authenticationToken', response.authenticationToken);
        this.localStorage.store('expiresAt', response.expiresAt);
      });
  }

  logout() {
    this.http.post('http://localhost:8082/api/auth/logout', this.refreshTokenPayload(),
      { responseType: 'text' })
      .subscribe(data => {
        console.log(data);
        }, error => {
        console.log(error);
      });
    this.clearLocalStorage();
  }

  getUserName() {
    return this.localStorage.retrieve('username');
  }
  getRefreshToken() {
    return this.localStorage.retrieve('refreshToken');
  }

  isLoggedIn(): boolean {
    return this.getToken() != null;
  }

  refreshTokenPayload(): RefreshTokenRequest {
    return {
      refreshToken: this.getRefreshToken(),
      username: this.getUserName()
    }
  }

  clearLocalStorage() {
    this.localStorage.clear('authenticationToken');
    this.localStorage.clear('username');
    this.localStorage.clear('refreshToken');
    this.localStorage.clear('expiresAt');
  }
}
