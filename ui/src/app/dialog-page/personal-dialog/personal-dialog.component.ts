import { Component, OnInit } from '@angular/core';
import {Dialog} from "../../shared/_models/dialog";
import {ActivatedRoute, Params} from "@angular/router";
import {MessageService} from "../../shared/services/message.service";
import {Observable} from "rxjs";
import {map, switchMap} from "rxjs/operators";
import {Message} from "../../shared/_models/message";

@Component({
  selector: 'app-personal-dialog',
  templateUrl: './personal-dialog.component.html',
  styleUrls: ['./personal-dialog.component.scss']
})
export class PersonalDialogComponent implements OnInit {

  isItme = true;
  dialog$:Observable<Message[]>;

  constructor(
    private route: ActivatedRoute,
    private messageService: MessageService,
  ) { }

  ngOnInit() {
    this.dialog$ = this.route.queryParams
      .pipe(
        switchMap((params: Params) => {
            console.log(params['user']);
            let user = params['user'];
            return this.messageService.getPersonalDialog(user)
              .pipe(
                map(
                  (req) => {
                    return req['messages']
                  }
                )
              )
          }
        )
      );
  }

  public getSenderInitials(sender: string): string {
    return sender && sender.substring(0, 2).toLocaleUpperCase();
  }

  public getSenderColor(sender: string): string {
    const alpha = '0123456789ABCDEFGHIJKLMNOPQRSTUVXYZ';
    const initials = this.getSenderInitials(sender);
    const value = Math.ceil((alpha.indexOf(initials[0]) + alpha.indexOf(initials[1])) * 255 * 255 * 255 / 50);
    return '#' + value.toString(16).padEnd(6, '0');
  }

}
