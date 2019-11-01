import {Component, ElementRef, Input, OnInit, ViewChild} from '@angular/core';
import {AllAccountInfo, Message, Offer, Task, User} from '../shared/interfaces';
import {UserService} from "../shared/services/user.service";

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.scss']
})
export class UserPageComponent implements OnInit {

  user: User;
  outgoingMessages: Message[];
  tasksLikeCustomer: Task[];
  tasksLikeExecutor: Task[];
  offers: Offer[];
  loading = true;
  constructor(
    private userService: UserService,
  ) { }

  ngOnInit() {
    this.userService.getProfile()
      .subscribe(
        (request: AllAccountInfo) => {
          this.loading = false;
          console.log(this.loading);
          this.user = request.user;
          this.outgoingMessages = request.outgoingMessages;
          this.tasksLikeCustomer = request.tasksLikeCustomer;
          this.tasksLikeExecutor = request.tasksLikeExecutor;
        }
      );

    this.userService.getOffersList()
      .subscribe(
        (request: Offer[]) => {
          this.offers = request;
        }
      )
  }

}
