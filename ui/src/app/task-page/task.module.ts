import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {TaskPageComponent} from './task-page.component';
import {TaskComponent} from './task/task.component';
import {RouterModule} from '@angular/router';
import { TaskListComponent } from './task-list/task-list.component';
import {MainLayoutComponent} from '../shared/components/main-layout/main-layout.component';
import {AuthGuard} from '../shared/services/auth.guard';
import {FormsModule} from '@angular/forms';
import {SearchPipe} from '../shared/pipes/search.pipe';
import {SharedModule} from '../shared/shared.module';
import {TaskService} from "../shared/services/task.service";

@NgModule({
  declarations: [
    TaskPageComponent,
    TaskComponent,
    TaskListComponent,
  ],
  imports: [
    CommonModule,
    SharedModule,
    RouterModule.forChild([
      {
        path: '', component: MainLayoutComponent, children: [
          {path: '', component: TaskPageComponent, canActivate: [AuthGuard]},
          {path: 'task/:id', component: TaskComponent, canActivate: [AuthGuard]},
        ]
      }
    ]),
    FormsModule,
  ],
  exports: [RouterModule, SearchPipe],
  providers: [TaskService]
})
export class TaskModule {
}
