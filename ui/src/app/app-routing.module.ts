import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthGuard} from "./shared/services/auth.guard";
import {Role} from "./shared/_models/role";
import {DialogListComponent} from "./shared/components/small-profile/dialog-list/dialog-list.component";

const routes: Routes = [
  {path: '', redirectTo: 'welcome', pathMatch: 'full'},
  {path: 'welcome',
    loadChildren: './welcome-page/welcome.module#WelcomeModule'},
  // {path: 'messages', component: DialogListComponent},
  {path: 'dialogs',
    loadChildren: './dialog-page/dialog-page.module#DialogPageModule', canActivate: [AuthGuard]},
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
