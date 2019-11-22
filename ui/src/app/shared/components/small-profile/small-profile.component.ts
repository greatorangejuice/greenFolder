import { Component, OnInit } from '@angular/core';
import {fadeInAnimation} from "../../_animations/fade-in.animation";
import {MessageService} from "../../services/message.service";
import {Observable} from "rxjs";
import {DialogCounter} from "../../_models/dialogCounter";

@Component({
  selector: 'app-small-profile',
  templateUrl: './small-profile.component.html',
  styleUrls: ['./small-profile.component.scss'],
  animations: [fadeInAnimation],

  // attach the fade in animation to the host (root) element of this component
  host: { '[@fadeInAnimation]': '' }
})
export class SmallProfileComponent implements OnInit {

  counter$:Observable<DialogCounter>;

  constructor(
    private messageService: MessageService,
  ) { }

  ngOnInit() {
   this.counter$ = this.messageService.getAllUnreadDialogues();
  }

}
