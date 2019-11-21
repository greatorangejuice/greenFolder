import {Component, Input, OnInit} from '@angular/core';
import {Task} from "../../shared/interfaces";

@Component({
  selector: 'app-user-tasks-lists',
  templateUrl: './user-tasks-lists.component.html',
  styleUrls: ['./user-tasks-lists.component.scss']
})
export class UserTasksListsComponent implements OnInit {

  @Input() taskLikeCustomer: Task[];
  @Input() taskLikeExecutor: Task[];
  visibleTasks = 'ALLTASKS';
  checkedList = 'CUSTOMER';

  constructor() { }

  ngOnInit() {
  }

  changeStatus(status: string) {
    this.visibleTasks = status;
  }

  changeList(status: string) {
    this.checkedList = status;
  }
}
