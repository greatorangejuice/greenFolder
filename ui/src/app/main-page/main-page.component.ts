import { Component, OnInit } from '@angular/core';
import {MatDialog} from "@angular/material";

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.scss']
})
export class MainPageComponent implements OnInit {

  constructor(
    public dialog: MatDialog,
  ) { }

  ngOnInit() {
  }

  openRules() {
    const dialogRef = this.dialog.open(RulesContent);

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }

}

@Component({
  selector: 'rules-content',
  templateUrl: 'rules-content.html',
})
export class RulesContent {}
