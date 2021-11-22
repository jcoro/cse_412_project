import {Component, OnInit} from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms';
import {LoginRequest} from './login';
import {Router, ActivatedRoute} from '@angular/router';
import {AuthService} from "../service/auth.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  loginRequest: LoginRequest;
  isError: boolean;
  loading: boolean = false;
  submitted: boolean;
  invalidCredential: boolean = true;
  endpoint: string = '/login';

  constructor(private activatedRoute: ActivatedRoute,
              private authService: AuthService,
              private router: Router,
              private route: ActivatedRoute) {
    if (authService.isLoggedIn()) {
      this.router.navigate(['/home']);
    }
    this.loginRequest = {
      username: '',
      password: ''
    };
  }

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required)
    });

    this.route.queryParams
      .subscribe(params => this.endpoint = params['return'] || '/login');
  }

  login() {
    this.loading = true;
    this.submitted = true;
    this.loginRequest.username = this.loginForm.get('username').value;
    this.loginRequest.password = this.loginForm.get('password').value;

    this.authService.login(this.loginRequest).subscribe(() => {
      this.isError = false;
      this.invalidCredential = true;
      this.router.navigateByUrl(this.endpoint.includes('login') ? '/home' : this.endpoint);
    }, error => {
      this.isError = true;
      console.log(error);
      this.invalidCredential = false;
    });

    this.loading = false;
  }
}
