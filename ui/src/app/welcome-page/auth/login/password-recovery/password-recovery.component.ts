import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../../../shared/services/auth.service";
import {AlertService} from "../../../../shared/services/alert.service";
import {Subscription} from "rxjs";
import {ActivatedRoute, Params} from "@angular/router";

@Component({
  selector: 'app-password-recovery',
  templateUrl: './password-recovery.component.html',
  styleUrls: ['./password-recovery.component.scss']
})
export class PasswordRecoveryComponent implements OnInit, OnDestroy {

  form: FormGroup;
  disabled = false;
  stream$: Subscription;

  constructor(
    private authService: AuthService,
    private alertService: AlertService,
  ) { }

  ngOnInit() {
    this.form = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email])
    })
  }

  ngOnDestroy() {
    if (this.stream$) {
      this.stream$.unsubscribe()
    }
  }

  submit() {
    const email = this.form.value.email;
    this.disabled = true;
    this.stream$ = this.authService.restorePassword(email)
      .subscribe(
        () => {
          this.disabled = false;
          this.alertService.success(`На ящик ${email} была отправлена инструкция по восстановлению доступа!`)
        },
      )
  }
}
