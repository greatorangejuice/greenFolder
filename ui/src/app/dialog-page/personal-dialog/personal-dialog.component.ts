import { Component, OnInit } from '@angular/core';
import {Dialog} from "../../shared/_models/dialog";

@Component({
  selector: 'app-personal-dialog',
  templateUrl: './personal-dialog.component.html',
  styleUrls: ['./personal-dialog.component.scss']
})
export class PersonalDialogComponent implements OnInit {

  isItme = true;

  messages: Dialog[] = [
    {id: 1, from: 'Tommy', forWho: 'Test', message: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eaque, veniam.'},
    {id: 2, from: 'Test', forWho: 'Tommy', message: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eaque, veniam.'},
    {id: 3, from: 'Tommy', forWho: 'Test', message: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eaque, veniam.'},
  ];

  constructor() { }

  ngOnInit() {
  }

}
