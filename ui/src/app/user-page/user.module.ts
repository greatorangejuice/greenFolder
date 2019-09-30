import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {UserPageComponent} from './user-page.component';
import {UserLayoutComponent} from './shared/components/user-layout/user-layout.component';
import {MatButtonModule, MatButtonToggleModule, MatTableModule} from '@angular/material';
import {SharedModule} from '../shared/shared.module';
import {MainLayoutComponent} from '../shared/components/main-layout/main-layout.component';

@NgModule({
  declarations: [
    UserPageComponent,
    UserLayoutComponent,
  ],
  imports: [
    CommonModule,
    SharedModule,
    RouterModule.forChild([
      {
        path: '', component: MainLayoutComponent, children: [
          {path: '', component: UserPageComponent},
        ]
      }
    ]),
    MatButtonModule,
    MatButtonToggleModule,
    MatTableModule,
  ],
  exports: [RouterModule]
})
export class UserModule {}
