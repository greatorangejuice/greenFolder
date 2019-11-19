import { Component, OnInit } from '@angular/core';
import {slideInOutAnimation} from "../shared/_animations/slide-in-out.animation";
import {Location} from "@angular/common";

@Component({
  selector: 'app-dialog-page',
  templateUrl: './dialog-page.component.html',
  styleUrls: ['./dialog-page.component.scss'],
  animations: [slideInOutAnimation],
  host: { '[@slideInOutAnimation]': '' }
})
export class DialogPageComponent implements OnInit {

  constructor(
    private _location: Location,
  ) { }

  ngOnInit() {
  }

  goBack() {
    this._location.back();
  }


}
