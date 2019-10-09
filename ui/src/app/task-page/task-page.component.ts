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

  tasks = [

  ];

  constructor(
    private auth: AuthService,
  ) { }

  ngOnInit() {
  }

}
