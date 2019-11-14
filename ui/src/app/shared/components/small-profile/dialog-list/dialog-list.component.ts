import { Component, OnInit } from '@angular/core';
import {slideInOutAnimation} from "../../../_animations/slide-in-out.animation";
import {Location} from '@angular/common'

@Component({
  selector: 'app-dialog-list',
  templateUrl: './dialog-list.component.html',
  styleUrls: ['./dialog-list.component.scss'],
  animations: [slideInOutAnimation],
  host: { '[@slideInOutAnimation]': '' }
})
export class DialogListComponent implements OnInit {

  constructor(
    private _location: Location,
  ) { }

  ngOnInit() {
  }

  goBack() {
    this._location.back();
  }

}
