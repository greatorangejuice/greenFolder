import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {User} from '../../../shared/interfaces';
import {AuthService} from '../../../shared/services/auth.service';
import {switchMap, tap} from 'rxjs/operators';
import {Router} from '@angular/router';
import {MustMatch} from './password.validators';
import {Observable} from "rxjs";
import {AlertService} from "../../../shared/services/alert.service";
@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})

export class RegistrationComponent implements OnInit {
  form: FormGroup;
  hide = true;
  universityList$: Observable<any>;
  constructor(
    private authService: AuthService,
    private router: Router,
    private formBuilder: FormBuilder,
    private alertService: AlertService,
  ) { }

  ngOnInit() {
   this.universityList$ =  this.authService.getUniversityList()
     .pipe(
       tap(
         (req) => {
           console.log(req);}
       )
     );

    this.form = this.formBuilder.group({
      username: ['', [Validators.required, Validators.minLength(2)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', Validators.required],
      name: new FormControl(''),
      surname: new FormControl(''),
      city: new FormControl('Minsk'),
      university: new FormControl(),
      faculty: new FormControl(''),
      course: new FormControl(''),
    }, {
      validator: MustMatch('password', 'confirmPassword')
    });
  }

  submit() {
    console.log(this.form);
    const user: User = {
      username: this.form.value.username,
      email: this.form.value.email,
      password: this.form.value.password,
      name: this.form.value.name,
      surname: this.form.value.surname,
      city: this.form.value.city,
      university: this.form.value.university,
      faculty: this.form.value.faculty,
      course: this.form.value.course,
    };
    console.log(user);
    this.authService.signup(user)
      .subscribe(
        (req) => {
          console.log(req);
          this.alertService.success('Successful registered!')
          },
        (err) => {
          console.log(err);
          this.alertService.danger('Bad request')
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

  // checkForEmail(control: FormControl): Promise<any> {
  //   return new Promise<any>( resolve => {
  //     setTimeout( () => {
  //       const email = {email: control.value};
  //       this.authService.checkEmail(email).subscribe(
  //         (res) => {
  //           if (res === 'exist') {
  //             resolve(null)
  //           } else {
  //             resolve({'emailInUse': true} )
  //           }
  //         }
  //       )
  //     }, 2000 );
  //   })
  // }
}
