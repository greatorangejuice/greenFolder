import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {AdminPageComponent} from './admin-page.component';
import {MainLayoutComponent} from '../shared/components/main-layout/main-layout.component';
import {SharedModule} from '../shared/shared.module';
import {AllUsersComponent, EditUserModal} from './all-users-list/all-users.component';
import {AdminService} from "../shared/services/admin.service";
import {HTTP_INTERCEPTORS} from "@angular/common/http";
import {AuthInterceptor} from "../shared/auth.interceptor";
import {CatchErrorInterceptor} from "../shared/interceptors/catch-error.interceptor";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatOptionModule, MatSelectModule} from "@angular/material";

@NgModule({
  declarations: [AdminPageComponent, AllUsersComponent, EditUserModal],
  entryComponents: [EditUserModal, AllUsersComponent],
  imports: [
    CommonModule,
    SharedModule,
    RouterModule.forChild([
      {
        path: '', component: MainLayoutComponent, children: [
          {path: '', component: AdminPageComponent}
        ]
      }
    ]),
    FormsModule,
    ReactiveFormsModule,
    MatOptionModule,
    MatSelectModule,
  ],
  exports: [],
  providers: [AdminService, {provide: HTTP_INTERCEPTORS, multi: true, useClass: AuthInterceptor}, {provide: HTTP_INTERCEPTORS, multi: true, useClass: CatchErrorInterceptor}],
})
export class AdminModule {}
