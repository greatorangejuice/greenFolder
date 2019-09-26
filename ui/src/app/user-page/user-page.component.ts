import {Component, ElementRef, Input, OnInit, ViewChild} from '@angular/core';
import {Task} from '../shared/interfaces';

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.scss']
})
export class UserPageComponent implements OnInit {

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
      status: 'available',
    },
  ];

  @ViewChild('aria', {static: false}) elemAria: ElementRef;
  constructor() { }

  ngOnInit() {
    console.log(this.elemAria);
  }

}
