import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {SharedModule} from "../shared/shared.module";
import {RouterModule} from "@angular/router";
import {MainLayoutComponent} from "../shared/components/main-layout/main-layout.component";
import {MainPageComponent, RulesContent} from "./main-page.component";
import {OrderFormComponent, PreSubmitForm} from './order-form/order-form.component';
import {ReactiveFormsModule} from "@angular/forms";
import {MatRadioModule, MatSelectModule, MatStepperModule} from "@angular/material";

@NgModule({
  declarations: [
    MainPageComponent,
    RulesContent,
    OrderFormComponent,
    PreSubmitForm,
  ],
  entryComponents: [MainPageComponent, RulesContent, OrderFormComponent, PreSubmitForm],
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
    MatStepperModule,
    MatSelectModule,
    MatRadioModule,
  ],
  exports: [
    RouterModule,
  ],
})
export class MainPageModule {

}
