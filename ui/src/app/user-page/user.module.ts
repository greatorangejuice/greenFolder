import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {UserPageComponent} from './user-page.component';
import {SharedModule} from '../shared/shared.module';
import {MainLayoutComponent} from '../shared/components/main-layout/main-layout.component';
import {ProfileComponent} from './profile/profile.component';
import {NotificationListComponent, NotificationModal} from './notification-list/notification-list.component';
import { UserTasksListsComponent } from './user-tasks-lists/user-tasks-lists.component';
import {MatDialogModule} from "@angular/material";
import {UserService} from "../shared/services/user.service";
import {ReactiveFormsModule} from "@angular/forms";
import { EditProfileComponent } from './profile/edit-profile/edit-profile.component';
import {HTTP_INTERCEPTORS} from "@angular/common/http";
import {AuthInterceptor} from "../shared/auth.interceptor";
import {AuthGuard} from "../shared/services/auth.guard";

@NgModule({
  declarations: [
    UserPageComponent,
    ProfileComponent,
    NotificationListComponent,
    UserTasksListsComponent,
    NotificationModal,
    EditProfileComponent,
  ],
  entryComponents: [NotificationListComponent,NotificationModal, EditProfileComponent, ProfileComponent],
  imports: [
    CommonModule,
    SharedModule,
    RouterModule.forChild([
      {
        path: '', component: MainLayoutComponent, children: [
          {path: '', component: UserPageComponent, canActivate: [AuthGuard]},
          {path: 'profile', component: ProfileComponent, canActivate: [AuthGuard]},
        ]
      }
    ]),
    MatDialogModule,
    ReactiveFormsModule,
  ],
  exports: [RouterModule],
  providers: [UserService, {provide: HTTP_INTERCEPTORS, multi: true, useClass: AuthInterceptor}]
})
export class UserModule {}
