import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {User} from '../../../shared/interfaces';
import {AuthService} from '../../../shared/services/auth.service';
import {switchMap, tap} from 'rxjs/operators';
import {Router} from '@angular/router';
import {MustMatch} from './password.validators';
@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})

export class RegistrationComponent implements OnInit {
  form: FormGroup;
  hide = true;
  universityList = [
    {id: 'bsuir', name: 'Belorussian State University'},
    {id: 'bntu', name: 'Belorussian State'},
    {id: 'bsu', name: 'Belorussian State'},
  ];
  constructor(
    private authService: AuthService,
    private router: Router,
    private formBuilder: FormBuilder,
  ) { }

  ngOnInit() {
    this.form = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email], this.checkForEmail.bind(this)],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', Validators.required],
      name: new FormControl(''),
      surname: new FormControl(''),
      city: new FormControl('Minsk'),
      university: new FormControl(),
    }, {
      validator: MustMatch('password', 'confirmPassword')
    });
  }

  checkForEmail(control: FormControl): Promise<any> {
    return new Promise<any>( resolve => {
      setTimeout( () => {
        this.authService.checkEmail(control.value).subscribe(
          (res) => {
            if (res === 'exist') {
              resolve(null)
            } else {
              resolve({'emailInUse': true} )
            }
          }
        )
      }, 2000 );
    })
  }

  submit() {
    console.log(this.form);
    const user: User = {
      email: this.form.value.email,
      password: this.form.value.password,
      subjects: {
        math: this.form.value.math,
        programming: this.form.value.programming,
        electricalChains: this.form.value.electricalChains,
      }
    };

    return;
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
