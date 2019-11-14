import { Component, OnInit } from '@angular/core';
import {fadeInAnimation} from "../../_animations/fade-in.animation";

@Component({
  selector: 'app-small-profile',
  templateUrl: './small-profile.component.html',
  styleUrls: ['./small-profile.component.scss'],
  animations: [fadeInAnimation],

  // attach the fade in animation to the host (root) element of this component
  host: { '[@fadeInAnimation]': '' }
})
export class SmallProfileComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
