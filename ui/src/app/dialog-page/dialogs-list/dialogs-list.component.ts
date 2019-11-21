import { Component, OnInit } from '@angular/core';
import {Observable} from "rxjs";
import {Router} from "@angular/router";
import {Dialog} from "../../shared/_models/dialog";
import {MessageService} from "../../shared/services/message.service";
import {map, tap} from "rxjs/operators";
import {PreviewDialog} from "../../shared/_models/previewDialog";

@Component({
  selector: 'app-dialogs-list',
  templateUrl: './dialogs-list.component.html',
  styleUrls: ['./dialogs-list.component.scss']
})
export class DialogsListComponent implements OnInit {

  searchValue: string;
  dialogs$: Observable<PreviewDialog[]>;
  isLoading = true;
  errorMessage = '';
  date = new Date();

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
        map(
          (req) => {
            console.log(req);
            const username = localStorage.getItem('username');
            const keys = Object.keys(req['dialogues']);
            let uniqueKeys: PreviewDialog[] = [];
            keys.forEach((key) => {
              const arrayMessageLength = req['dialogues'][key].length;
              uniqueKeys.push({user: key.replace(username, '').trim(), message: req['dialogues'][key][arrayMessageLength-1].message});
            });
            console.log(uniqueKeys);
            this.isLoading = false;
            return uniqueKeys;
          },
          (error) => {
            this.isLoading = false;
            this.errorMessage = error;
            console.log(this.errorMessage);
          }
        )
      )
  }

  goToDialog(user: string) {
    this.router.navigate([`dialogs`, 'personal'], {
      queryParams: {
        user: user,
      },
    })
  }
}
