import { Component, OnInit } from '@angular/core';
import {Observable} from "rxjs";
import {Router} from "@angular/router";

@Component({
  selector: 'app-dialogs-list',
  templateUrl: './dialogs-list.component.html',
  styleUrls: ['./dialogs-list.component.scss']
})
export class DialogsListComponent implements OnInit {

  searchValue: string;
  dialogs$: Observable<any>;

  constructor(
    private router: Router,
  ) { }

  ngOnInit() {
  }

  goToDialog(id: string) {
    this.router.navigate([`/${id}`])
  }
}
