import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {AuthService} from '../../../shared/services/auth.service';
import {User} from '../../../shared/interfaces';
import {delay, switchMap} from 'rxjs/operators';
import {pipe} from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  form: FormGroup;
  submitted = false;
  message: string;

  constructor(
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute,
  ) { }

  // Сделать двойной запрос: сначала логин, с отдельным вытягиваением  id, после запрос на получение данных пользователя.

  ngOnInit() {
    this.route.queryParams.subscribe(
      (params: Params) => {
        // if (params['loginAgain']) {
        //   this.message = 'Please, login';
        // }
       this.message =
         (params.loginAgain) ? 'Please, login' :
         (params.logout) ? 'Unlogin' :
           (params.authFailed) ? 'Session is finished. Please, login again.' : '';
      }
    );

    this.form = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required]),
    });
  }

  submit() {
    if (this.form.invalid) {
      return;
    }

    this.submitted = true;

    const user: User = {
      email: this.form.value.email,
      password: this.form.value.password,
    };

    return this.authService.login(user)
      .subscribe(
        () => {
        this.submitted = false;
        this.router.navigate(['/tasks']);
        },
        (error) => {
          if (error.name === 'HttpErrorResponse') {
            console.log(error.error.error.message);
            const errMessage: string = error.error.error.message;
            this.message =
              'EMAIL_NOT_FOUND' ? 'Email or Password is not correct' : '';
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
}
