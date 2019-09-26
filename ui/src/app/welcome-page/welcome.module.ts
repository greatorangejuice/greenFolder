import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {WelcomePageComponent} from './welcome-page.component';
import {LoginComponent} from './auth/login/login.component';
import {RegistrationComponent} from './auth/registration/registration.component';
import {SharedModule} from '../shared/shared.module';
import {MatCheckboxModule} from '@angular/material';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    RouterModule.forChild([
      {
        path: '', component: WelcomePageComponent, children: [
          {path: '', component: LoginComponent},
          {path: 'registration', component: RegistrationComponent},
        ]
      },
    ]),
    ReactiveFormsModule,
    SharedModule,
    MatCheckboxModule,
  ],
  exports: [
    RouterModule
  ],
  declarations: [
    WelcomePageComponent,
    LoginComponent,
    RegistrationComponent
  ]
})
export class WelcomeModule {
}
