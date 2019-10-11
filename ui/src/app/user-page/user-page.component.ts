import {Component, ElementRef, Input, OnInit, ViewChild} from '@angular/core';
import {AllAccountInfo, Message, Task, User} from '../shared/interfaces';
import {UserService} from "../shared/services/user.service";

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.scss']
})
export class UserPageComponent implements OnInit {

  user: User;
  outgoingMessages: Message[];
  tasks: Task[];
  loading = true;
  diameter = 70;

  constructor(
    private userService: UserService,
  ) { }

  ngOnInit() {
    this.userService.getProfile()
      .subscribe(
        (request: AllAccountInfo) => {
          this.loading = false;
          this.user = request.user;
          this.outgoingMessages = request.outgoingMessages;
          this.tasks = request.tasksLikeCustomer.concat(request.tasksLikeExecutor);
        }
      )
  }
}
