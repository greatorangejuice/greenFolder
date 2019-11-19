import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {SharedModule} from "../shared/shared.module";
import {RouterModule} from "@angular/router";
import {DialogPageComponent} from "./dialog-page.component";
import { DialogsListComponent } from './dialogs-list/dialogs-list.component';
import {MainLayoutComponent} from "../shared/components/main-layout/main-layout.component";
import {TaskPageComponent} from "../task-page/task-page.component";
import {AuthGuard} from "../shared/services/auth.guard";
import {TaskComponent} from "../task-page/task/task.component";
import { PersonalDialogComponent } from './personal-dialog/personal-dialog.component';
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    DialogPageComponent,
    DialogsListComponent,
    PersonalDialogComponent,
  ],
  imports: [
    CommonModule,
    SharedModule,
    RouterModule.forChild([
      {
        path: '', component: DialogPageComponent, children: [
          {path: '', component: DialogsListComponent, canActivate: [AuthGuard]},
          {path: 'dialogs/:id', component: PersonalDialogComponent, canActivate: [AuthGuard]}
        ]
      }
    ]),
    FormsModule
  ]
})

export class DialogPageModule {}


// RouterModule.forChild([
// //   {
// //     path: '', component: MainLayoutComponent, children: [
// //       {path: '', component: TaskPageComponent, canActivate: [AuthGuard]},
// //       {path: 'task/:id', component: TaskComponent, canActivate: [AuthGuard]},
// //     ]
// //   }
// ]),
