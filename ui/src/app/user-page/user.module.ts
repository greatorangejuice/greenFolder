import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {UserPageComponent} from './user-page.component';
import {SharedModule} from '../shared/shared.module';
import {MainLayoutComponent} from '../shared/components/main-layout/main-layout.component';
import { ProfileComponent } from './profile/profile.component';
import {NotificationListComponent, NotificationModal} from './notification-list/notification-list.component';
import { UserTasksListsComponent } from './user-tasks-lists/user-tasks-lists.component';
import {MatDialogModule} from "@angular/material";

@NgModule({
  declarations: [
    UserPageComponent,
    ProfileComponent,
    NotificationListComponent,
    UserTasksListsComponent,
    NotificationModal,
  ],
  entryComponents: [NotificationListComponent,NotificationModal],
  imports: [
    CommonModule,
    SharedModule,
    RouterModule.forChild([
      {
        path: '', component: MainLayoutComponent, children: [
          {path: '', component: UserPageComponent},
          {path: 'profile', component: ProfileComponent},
        ]
      }
    ]),
    MatDialogModule,
  ],
  exports: [RouterModule]
})
export class UserModule {}
