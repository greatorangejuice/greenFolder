import { Component, OnInit } from '@angular/core';
import {Task} from '../shared/interfaces';
import {AuthService} from '../shared/services/auth.service';
import {TaskService} from "../shared/services/task.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-task-page',
  templateUrl: './task-page.component.html',
  styleUrls: ['./task-page.component.scss']
})
export class TaskPageComponent implements OnInit {

  searchValue: string;
  // tasksStream$: Observable<Task[]>;
  tasks$: Observable<Task[]>;

  constructor(
    private taskService: TaskService,
  ) { }

  ngOnInit() {
    this.tasks$ = this.taskService.getAllTasks();
  }

}
