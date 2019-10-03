import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AuthGuard} from "./shared/services/auth.guard";

const routes: Routes = [
  {path: '', redirectTo: 'welcome', pathMatch: 'full'},
  {path: 'welcome', loadChildren: './welcome-page/welcome.module#WelcomeModule'},
  {path: 'main', loadChildren: './main-page/main-page.module#MainPageModule', canActivate: [AuthGuard]},
  {path: 'tasks', loadChildren: './task-page/task.module#TaskModule', canActivate: [AuthGuard]},
  {path: 'user', loadChildren: './user-page/user.module#UserModule', canActivate: [AuthGuard]},
  {path: 'shuffle', loadChildren: './admin-page/admin.module#AdminModule', canActivate: [AuthGuard]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
