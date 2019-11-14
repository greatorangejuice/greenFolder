import { Component, OnInit } from '@angular/core';
import {slideInOutAnimation} from "../../../_animations/slide-in-out.animation";

@Component({
  selector: 'app-message-list',
  templateUrl: './message-list.component.html',
  styleUrls: ['./message-list.component.scss'],
  animations: [slideInOutAnimation],
  host: { '[@slideInOutAnimation]': '' }
})
export class MessageListComponent implements OnInit {

  constructor(
    // private _location: Location,
  ) { }

  ngOnInit() {
  }

  goBack() {
    // this._location.back();
  }

}
