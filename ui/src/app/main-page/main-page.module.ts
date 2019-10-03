import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {SharedModule} from "../shared/shared.module";
import {RouterModule} from "@angular/router";
import {MainLayoutComponent} from "../shared/components/main-layout/main-layout.component";
import {MainPageComponent} from "./main-page.component";
import {AuthGuard} from "../shared/services/auth.guard";
import {MatListModule} from "@angular/material";
import { OrderFormComponent } from './order-form/order-form.component';

@NgModule({
  declarations: [
    MainPageComponent,
    OrderFormComponent,
  ],
  imports: [
    CommonModule,
    SharedModule,
    RouterModule.forChild([
      {
        path: '', component: MainLayoutComponent, children: [
          {path: '', component: MainPageComponent, canActivate: [AuthGuard]},
          {path: 'order', component: OrderFormComponent, canActivate: [AuthGuard]}
        ]
      }
    ]),
    MatListModule,
  ],
  exports: [
    RouterModule,
  ],
})
export class MainPageModule {

}
