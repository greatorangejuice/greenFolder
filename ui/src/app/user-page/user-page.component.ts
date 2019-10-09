import {Component, ElementRef, Input, OnInit, ViewChild} from '@angular/core';
import {AllAccountInfo, Task, User} from '../shared/interfaces';
import {UserService} from "../shared/services/user.service";

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.scss']
})
export class UserPageComponent implements OnInit {

  user: User;


  constructor(
    private userService: UserService,
  ) { }

  ngOnInit() {
    this.userService.getProfile()
      .subscribe(
        (request: AllAccountInfo) => {
          this.user = request.user;
        }
      )
  }
}
