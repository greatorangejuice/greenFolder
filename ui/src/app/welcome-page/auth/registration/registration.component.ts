import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {User} from '../../../shared/interfaces';
import {AuthService} from '../../../shared/services/auth.service';
import {switchMap, tap} from 'rxjs/operators';
import {Router} from '@angular/router';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {
  form: FormGroup;

  constructor(
    private authService: AuthService,
    private router: Router,
  ) { }

  ngOnInit() {
    this.form = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required, Validators.minLength(6)]),
      password_repeat: new FormControl('', [Validators.required, Validators.minLength(6)]),
      math: new FormControl(false),
      programming: new FormControl(false),
      electricalChains: new FormControl(false),
    });
  }

  submit() {
    // console.log(this.form);
    const user: User = {
      email: this.form.value.email,
      password: this.form.value.password,
      subjects: {
        math: this.form.value.math,
        programming: this.form.value.programming,
        electricalChains: this.form.value.electricalChains,
      }
    };
    // console.log(user);
    this.authService.signup(user)
      .pipe(
        tap((req) => {
          console.log(req); }),
        switchMap(
          () => {
            return this.authService.login(user);
          }
        ),
        switchMap(
          (req) => {
            console.log(req);
            const userInfo = {
              user_id: req.localId,
              subjects: user.subjects,
            };
            return this.authService.sendUserData(userInfo)
              .pipe(
                tap(() => {
                  this.router.navigate(['/tasks']);
                })
              );
          }
        )
      )
      .subscribe(
        (req) => {
          console.log('subscription');
          console.log(req);
          }
        );
  }

  // confirmedPass(control: FormControl) {
  //   if (control.value && this.form) {
  //     console.log(control.value);
  //     console.log(this.form.value['password']);
  //     return {
  //       ['lengthError']: true,
  //     };
  //   }
  //   return null;
  // }
}
