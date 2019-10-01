import {Component, ElementRef, Input, OnInit, ViewChild} from '@angular/core';
import {Task} from '../shared/interfaces';

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.scss']
})
export class UserPageComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }
}
