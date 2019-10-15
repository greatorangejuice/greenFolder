import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../../shared/services/auth.service";
import {ActivatedRoute} from "@angular/router";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-accept-mail-page',
  templateUrl: './accept-mail-page.component.html',
  styleUrls: ['./accept-mail-page.component.scss']
})
export class AcceptMailPageComponent implements OnInit {

  stream$: Subscription;

  constructor(
    private authService: AuthService,
    private route: ActivatedRoute,
  ) { }

  ngOnInit() {
    this.stream$ = this.authService.restorePassword(email)
      .subscribe(
        () => {

        },
      )
  }

}
