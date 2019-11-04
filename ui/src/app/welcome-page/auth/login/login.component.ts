import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {AuthService} from '../../../shared/services/auth.service';
import {User} from '../../../shared/interfaces';
import {delay, switchMap} from 'rxjs/operators';
import {pipe} from 'rxjs';
import {PasswordRecoveryComponent} from "./password-recovery/password-recovery.component";
import {MatDialog} from "@angular/material";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  form: FormGroup;
  submitted = false;
  message: string;
  username = '';
  hide = true;

  constructor(
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute,
    public dialog: MatDialog,
  ) { }

  ngOnInit() {
    this.route.queryParams.subscribe(
      (params: Params) => {
       this.message =
         (params.loginAgain) ? 'Please, login' :
         (params.logout) ? 'Unlogin' :
         (params.authFailed) ? 'Session is finished. Please, login again.' :
         (params.authSuccessful) ? 'Now you can use your new account' :
         (params.isChangedPassword) ? 'Welcome back :) ' : '';
       this.username = params.usernameForLogin;
       if (params.username) {
         this.username = params.username;
       }
      }
    );
    this.form = new FormGroup({
      username: new FormControl(this.username, [Validators.required]),
      password: new FormControl('', [Validators.required]),
    });
  }

  submit() {
    if (this.form.invalid) {
      return;
    }
    this.submitted = true;
    const user: User = {
      username: this.form.value.username,
      password: this.form.value.password,
    };

    return this.authService.login(user)
      .subscribe(
        (response) => {
        this.submitted = false;
        this.router.navigate(['/main']);
        localStorage.setItem('username', response.username);
        },
        (error) => {
          console.log(error);
          if (error.name === 'HttpErrorResponse' && error.status === 403) {
            // console.log('Ошибка в логине');
            // console.log(error);
            // console.log(error.message);
            this.message =
              'TEST' ? 'Username or Password is not correct' : '';
              // 'EMAIL_NOT_FOUND' ? 'Email or Password is not correct' : ''
            // this.message = 'Connection error. Please, check your internet connection.';
            // if (!this.message) {
            //   this.message = 'Connection error. Please, check your internet connection.';
            // }
            setTimeout(
              () => {
                this.submitted = false;
              }, 2000
            );
          }
        }
      );
  }

  openRecoveryForm() {
    const dialogRef = this.dialog.open(PasswordRecoveryComponent);
    dialogRef.afterClosed().subscribe();
  }
}
