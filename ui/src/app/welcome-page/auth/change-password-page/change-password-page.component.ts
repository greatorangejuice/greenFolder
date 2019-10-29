import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Params, Router} from "@angular/router";
import {AuthService} from "../../../shared/services/auth.service";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {MustMatch} from "../../../shared/forms-validators/same-fileds-values";

@Component({
  selector: 'app-change-password-page',
  templateUrl: './change-password-page.component.html',
  styleUrls: ['./change-password-page.component.scss']
})
export class ChangePasswordPageComponent implements OnInit {

  form: FormGroup;
  recoveryKey: string;
  hide = true;
  username = '';

  constructor(
    private route: ActivatedRoute,
    private auth: AuthService,
    private formBuilder: FormBuilder,
    private router: Router,
  ) { }

  ngOnInit() {
    this.route.queryParams.subscribe(
      (params: Params) => {
        this.recoveryKey = params.restoreKey;
      }
    );

    this.form = this.formBuilder.group({
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', Validators.required],
    }, {
      validator: MustMatch('password', 'confirmPassword')
    });

    this.auth.checkLink(this.recoveryKey)
      .subscribe(
        (response) => {
            this.username = response.username;
        }
      )

  }

  setNewPassword() {
    const newPassword = this.form.value.password;
    this.auth.setNewPassword(newPassword, this.recoveryKey)
      .subscribe(
        (response) => {
         this.router.navigate(['welcome'], {
           queryParams: {
             isChangedPassword: true,
             username: this.username,
           }
           });
        }
      )
  }

}
