import {Component} from '@angular/core';
import {fadeInAnimation} from "../shared/_animations/fade-in.animation";

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.scss'],
  animations: [fadeInAnimation],
  host: { '[@fadeInAnimation]': '' }
})
export class AdminPageComponent {

}
