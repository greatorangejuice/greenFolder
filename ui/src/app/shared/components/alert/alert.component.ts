import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {AlertService} from "../../services/alert.service";
import {Subscription} from "rxjs";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-alert',
  templateUrl: './alert.component.html',
  styleUrls: ['./alert.component.scss']
})
export class AlertComponent implements OnInit, OnDestroy {

  @Input() delay = 5000;

  text:string;
  type = 'success';
  alertSub$: Subscription;

  constructor(
    private alertService: AlertService,
    private _snackBar: MatSnackBar,
  ) { }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, {
      duration: 2000,
    });
  }

  ngOnInit() {
    this.alertSub$ = this.alertService.alert$
      .subscribe(alert => {
        this.text = alert.text;
        this.type = alert.type;
        this._snackBar.open(this.text, 'close', {duration: 3000, verticalPosition: "top"});

        const timeout = setTimeout( () => {
          clearTimeout(timeout);
          this.text = '';
        }, this.delay )
      })
  }

  ngOnDestroy() {
    if (this.alertSub$) {
      this.alertSub$.unsubscribe();
    }
  }

}
