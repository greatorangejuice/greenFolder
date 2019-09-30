import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';
import {ActivatedRoute, Params} from '@angular/router';
import {TaskService} from "../../shared/services/task.service";
import {Observable} from "rxjs";
import {switchMap, tap} from "rxjs/operators";

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class TaskComponent implements OnInit {

  // @Input() task;
  params: Params;
  id: string;
  task$: Observable<any>;

  constructor(
    private route: ActivatedRoute,
    private taskService: TaskService,
  ) { }

  ngOnInit() {
    // this.route.params
    //   .subscribe((params: Params) => {
    //     this.id = params['id'];
    //   });
    // Запрос на получение данных по id.
    // this.task$ = this.taskService.getTaskById()
    //   .subscribe(
    //     (req) => {
    //       console.log(req);
    //     }
    //   )
    this.task$ = this.route.params
      .pipe(
        tap((req) => {
          console.log(req);}),
        switchMap( (params:Params) => {
          this.id = params['id'];
          return this.taskService.getTaskById()
            .pipe(
              tap((req) => {
                console.log(req);
              })
            )
        } )
      )
  }
}
