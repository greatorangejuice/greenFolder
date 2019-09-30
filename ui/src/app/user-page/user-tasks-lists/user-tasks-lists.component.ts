import { Component, OnInit } from '@angular/core';
import {Task} from "../../shared/interfaces";

@Component({
  selector: 'app-user-tasks-lists',
  templateUrl: './user-tasks-lists.component.html',
  styleUrls: ['./user-tasks-lists.component.scss']
})
export class UserTasksListsComponent implements OnInit {

  visibleTasks = 'progress';

  tasks: Task[] = [
    {
      title: 'first',
      date: new Date(),
      text: 'text',
      author: 'Developer',
      id: 'fdgnsjfsdfsdf',
      status: 'progress',
    },
    {
      title: 'second',
      date: new Date(),
      text: 'text',
      author: 'Developer',
      id: 'sfsfsfsfsdf',
      status: 'finished',
    },
  ];

  // @ViewChild('aria', {static: false}) elemAria: ElementRef;
  constructor() { }

  ngOnInit() {
    // console.log(this.elemAria);
  }

  changeStatus(status: string) {
    this.visibleTasks = status;
  }

}
