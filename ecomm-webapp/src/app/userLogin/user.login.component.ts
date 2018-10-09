import {Component, OnInit} from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UserLoginService } from './user.login.service';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-user-login',
  templateUrl: './user.login.component.html'
})
export class UserLoginComponent implements OnInit {
  name:string;
  error = "";
  loginForm: FormGroup;
  loading = false;
  submitted = false;
  errormessage: Boolean = true;
  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: UserLoginService,
  ) {}
  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      email: [null, Validators.required],
      password: [null, Validators.required]
    });
  }
  // convenience getter for easy access to form fields
  get f() {
    return this.loginForm.controls;
  }
  onSubmit() {
    this.submitted = true;
    // stop here if form is invalid
    if (this.loginForm.invalid) {
      return;
    }
    console.log(this.f.email.value);
    this.loading = true;
    this.authenticationService
      .loginUser(this.f.email.value, this.f.password.value)
      .pipe(first())
      .subscribe(
        data => {
          this.router.navigate(["/catalog"]);
          location.reload();
        },
        error => {
          this.loading = false;
          this.errormessage = false;
        }
      );
  }
}
