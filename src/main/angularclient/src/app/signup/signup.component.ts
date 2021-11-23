import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthService} from "../service/auth.service";

@Component({templateUrl: 'signup.component.html'})
export class SignupComponent implements OnInit {
  registerForm: FormGroup;
  submitted = false;
  loading: boolean = false;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router,
  ) {
    if (authService.isLoggedIn()) {
      this.router.navigate(['/journal']);
    }
  }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  // convenience getter for easy access to form fields
  get getForm() {
    return this.registerForm.controls;
  }

  onSubmit() {
    this.loading = true;
    this.submitted = true;

    if (this.registerForm.invalid) {
      return;
    }
    this.authService.signup(this.registerForm.getRawValue())
      .subscribe(data => {
        console.log(data);
        this.router.navigate(['/login'],
          {queryParams: {registered: 'true'}});
      }, error => {
        console.log(error);
      });
    this.loading = false;
  }
}
