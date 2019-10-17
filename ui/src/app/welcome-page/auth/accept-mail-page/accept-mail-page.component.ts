import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../../shared/services/auth.service";
import {ActivatedRoute, Params} from "@angular/router";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-accept-mail-page',
  templateUrl: './accept-mail-page.component.html',
  styleUrls: ['./accept-mail-page.component.scss']
})
export class AcceptMailPageComponent implements OnInit {

  stream$: Subscription;
  activationKey: string;
  email: string;
  acceptedEmail = false;
  error = false;

  constructor(
    private authService: AuthService,
    private route: ActivatedRoute,
  ) { }

  ngOnInit() {

    this.route.queryParams.subscribe(
      (params: Params) => {
        this.activationKey = params.activationKey;
      }
    );

    if (this.activationKey) {
      this.authService.restorePassword(this.activationKey)
        .subscribe(
        )
    }
  }

  confirmEmail() {
    this.authService.confirmEmail(this.activationKey)
      .subscribe(
        () => {
          this.acceptedEmail = true;
        },
        () => {
          this.error = true;
        }
      );
  }
}
