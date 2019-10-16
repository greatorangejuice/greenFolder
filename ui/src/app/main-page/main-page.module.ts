import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {SharedModule} from "../shared/shared.module";
import {RouterModule} from "@angular/router";
import {MainLayoutComponent} from "../shared/components/main-layout/main-layout.component";
import {MainPageComponent, RulesContent} from "./main-page.component";
import { OrderFormComponent } from './order-form/order-form.component';
import {ReactiveFormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    MainPageComponent,
    RulesContent,
    OrderFormComponent,
  ],
  entryComponents: [MainPageComponent, RulesContent],
  imports: [
    CommonModule,
    SharedModule,
    RouterModule.forChild([
      {
        path: '', component: MainLayoutComponent, children: [
          {path: '', component: MainPageComponent,},
          {path: 'order', component: OrderFormComponent,}
        ]
      }
    ]),
    ReactiveFormsModule,
  ],
  exports: [
    RouterModule,
  ],
})
export class MainPageModule {

}
