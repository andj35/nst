import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/core/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  loading: boolean = false;
  submitted: boolean = false;
  loginError: boolean = false;
  redirect: boolean = false;

  constructor(private userService: UserService, private formBuilder: FormBuilder, private router: Router) {}

  ngOnInit(): void {
    this.loginForm = this.configureFormFields();
  }

  onSubmit() {
    this.submitted = true;
    if (this.loginForm.invalid) {
      return;
    }
    this.loading = true;
    this.login();
  }

  login() {
    this.userService.loginUser(this.loginForm.value).subscribe({
      next: loggedUser => {
        loggedUser.password = this.loginForm.get('password')!.value;
        sessionStorage.setItem('loggedUser', JSON.stringify(loggedUser));
        this.router.navigate(['/calendar']);
      },
      error: () => {
        this.loginError = true;
        this.loading = false;
      }
    });
  }

  get f() {
    return this.loginForm.controls;
  }


  private configureFormFields(): FormGroup {
    var form = this.formBuilder.group({
      username: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
    return form;
  }

}
