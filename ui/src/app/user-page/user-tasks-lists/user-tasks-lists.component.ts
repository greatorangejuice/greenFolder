import {Component, Input, OnInit} from '@angular/core';
import {Task} from "../../shared/interfaces";

@Component({
  selector: 'app-user-tasks-lists',
  templateUrl: './user-tasks-lists.component.html',
  styleUrls: ['./user-tasks-lists.component.scss']
})
export class UserTasksListsComponent implements OnInit {

  @Input() tasks: Task[];
  visibleTasks = 'ACTIVE';
  // ACTIVE, INACTIVE, INPROGRESS, DONE

  // @ViewChild('aria', {static: false}) elemAria: ElementRef;
  constructor() { }

  ngOnInit() {
    // console.log(this.elemAria);
  }

  changeStatus(status: string) {
    this.visibleTasks = status;
  }

}
