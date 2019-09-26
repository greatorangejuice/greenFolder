import { Component, OnInit } from '@angular/core';
import {Task} from '../shared/interfaces';
import {AuthService} from '../shared/services/auth.service';

@Component({
  selector: 'app-task-page',
  templateUrl: './task-page.component.html',
  styleUrls: ['./task-page.component.scss']
})
export class TaskPageComponent implements OnInit {

  searchValue: string;

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
      status: 'available',
    },
  ];

  constructor(
    private auth: AuthService,
  ) { }

  ngOnInit() {
  }

}
