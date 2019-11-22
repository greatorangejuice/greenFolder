import {AfterViewChecked, Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Params} from "@angular/router";
import {MessageService} from "../../shared/services/message.service";
import {Observable, Subscription} from "rxjs";
import {map, mergeMap, pluck, shareReplay, switchMap, tap} from "rxjs/operators";
import {Message} from "../../shared/_models/message";

@Component({
  selector: 'app-personal-dialog',
  templateUrl: './personal-dialog.component.html',
  styleUrls: ['./personal-dialog.component.scss']
})
export class PersonalDialogComponent implements OnInit {

  @ViewChild("textarea", {static: true}) textArea: ElementRef;
  sender: string;
  isItme = true;
  dialog$:Observable<Message[]>;
  dialogState$:Observable<void>;
  user$:Observable<Params>;
  username$:Observable<string>;

  constructor(
    private route: ActivatedRoute,
    private messageService: MessageService,
  ) { }

  ngOnInit() {

    this.user$ = this.route.queryParams.pipe(
      map((params:Params) => params['user']),
      // shareReplay(1)
    );

    // this.username$ = this.user$.pipe(pluck('user'));

    this.dialog$ = this.user$.pipe(
      switchMap(user => this.messageService.getPersonalDialog(user)),
      map(req => req['messages']),
    );

    this.user$.pipe(
      switchMap(user => this.messageService.setDialogAsViewed(user))
    ).subscribe()



    // console.log(this.textArea);
    // this.dialog$ = this.route.queryParams
    //   .pipe(
    //     switchMap((params: Params) => {
    //         console.log(params['user']);
    //         this.sender = params['user'];
    //       // console.log('====');
    //       // console.log(this.sender);
    //       // console.log('====');
    //       return this.messageService.getPersonalDialog(this.sender)
    //           .pipe(
    //             map(
    //               (req) => {
    //                 return req['messages']
    //               }
    //             )
    //           )
    //       }
    //     )
    //   );
    // if (this.dialog$) {
    //   this.dialogState$ = this.messageService.setDialogAsViewed(this.sender)
    // }
  }


  getSenderInitials(sender: string): string {
    return sender && sender.substring(0, 2).toLocaleUpperCase();
  }

  getSenderColor(sender: string): string {
    const alpha = '0123456789ABCDEFGHIJKLMNOPQRSTUVXYZ';
    const initials = this.getSenderInitials(sender);
    const value = Math.ceil((alpha.indexOf(initials[0]) + alpha.indexOf(initials[1])) * 255 * 255 * 255 / 50);
    return '#' + value.toString(16).padEnd(6, '0');
  }

}
