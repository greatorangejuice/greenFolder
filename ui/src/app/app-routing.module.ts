import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthGuard} from "./shared/services/auth.guard";
import {Role} from "./shared/_models/role";
import {DialogListComponent} from "./shared/components/small-profile/dialog-list/dialog-list.component";
import {MessageListComponent} from "./shared/components/small-profile/message-list/message-list.component";

const routes: Routes = [
  {path: '', redirectTo: 'welcome', pathMatch: 'full'},
  {path: 'welcome',
    loadChildren: './welcome-page/welcome.module#WelcomeModule'},
  {path: 'messages', component: DialogListComponent},
  {path: 'main',
    loadChildren: './main-page/main-page.module#MainPageModule', canActivate: [AuthGuard]},
  {path: 'tasks',
    loadChildren: './task-page/task.module#TaskModule', canActivate: [AuthGuard], data: {roles: [Role.User, Role.Executor]} },
  {path: 'user',
    loadChildren: './user-page/user.module#UserModule', canActivate: [AuthGuard]},
  {path: 'shuffle',
    loadChildren: './admin-page/admin.module#AdminModule', canActivate: [AuthGuard],data: {roles: [Role.Admin]},}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
