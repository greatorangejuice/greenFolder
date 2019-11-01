import {Component, Input, OnInit} from '@angular/core';
import {Offer} from "../../shared/interfaces";

@Component({
  selector: 'app-offers-list',
  templateUrl: './offers-list.component.html',
  styleUrls: ['./offers-list.component.scss']
})
export class OffersListComponent implements OnInit {

  @Input() offers: Offer[];

  constructor() { }

  ngOnInit() {
  }

}
