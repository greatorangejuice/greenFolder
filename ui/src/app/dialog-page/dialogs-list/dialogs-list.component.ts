import { Component, OnInit } from '@angular/core';
import {Observable} from "rxjs";
import {Router} from "@angular/router";
import {Dialog} from "../../shared/_models/dialog";
import {MessageService} from "../../shared/services/message.service";
import {tap} from "rxjs/operators";

@Component({
  selector: 'app-dialogs-list',
  templateUrl: './dialogs-list.component.html',
  styleUrls: ['./dialogs-list.component.scss']
})
export class DialogsListComponent implements OnInit {

  searchValue: string;
  dialogs$: Observable<Dialog[]>;
  isLoading = true;
  errorMessage = '';

  constructor(
    private router: Router,
    private messageService: MessageService,
  ) { }

  ngOnInit() {
    this.dialogs$ = this.getMessages();
  }

  getMessages() {
    return this.messageService.getDialogs()
      .pipe(
        tap(
          () => {
            this.isLoading = false;
            console.log(this.dialogs$);
          },
          (error) => {
            this.isLoading = false;
            this.errorMessage = error;
            console.log(this.errorMessage);
          }
        )
      )
  }

  goToDialog(id: number) {
    this.router.navigate([`/${id}`])
  }
}
