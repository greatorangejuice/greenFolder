import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {AdminPageComponent} from './admin-page.component';
import {MainLayoutComponent} from '../shared/components/main-layout/main-layout.component';
import {SharedModule} from '../shared/shared.module';

@NgModule({
  declarations: [AdminPageComponent],
  imports: [
    CommonModule,
    SharedModule,
    RouterModule.forChild([
      {path: '', component: MainLayoutComponent, children: [
          {path: '', component: AdminPageComponent}
        ]}
    ])
  ],
  exports: [],
})
export class AdminModule {}
