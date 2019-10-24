import {Component, Input, OnInit} from '@angular/core';
import {Task} from "../../shared/interfaces";
import {TaskService} from "../../shared/services/task.service";

@Component({
  selector: 'app-user-tasks-lists',
  templateUrl: './user-tasks-lists.component.html',
  styleUrls: ['./user-tasks-lists.component.scss']
})
export class UserTasksListsComponent implements OnInit {

  @Input() taskLikeCustomer: Task[];
  @Input() taskLikeExecutor: Task[];
  visibleTasks = 'ALLTASKS';

  constructor(
    private taskService: TaskService,
  ) { }

  ngOnInit() {
    this.taskService.getAllTasks()
      .subscribe(
        (response) => {
          console.log(response);
        }
      )
  }

  changeStatus(status: string) {
    this.visibleTasks = status;
  }

}
