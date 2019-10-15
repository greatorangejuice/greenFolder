import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {WelcomePageComponent} from './welcome-page.component';
import {LoginComponent} from './auth/login/login.component';
import {RegistrationComponent} from './auth/registration/registration.component';
import {SharedModule} from '../shared/shared.module';
import {MatCheckboxModule, MatSelectModule} from '@angular/material';
import { PasswordRecoveryComponent } from './auth/login/password-recovery/password-recovery.component';
import { ChangePasswordPageComponent } from './auth/change-password-page/change-password-page.component';
import { AcceptMailPageComponent } from './auth/accept-mail-page/accept-mail-page.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    RouterModule.forChild([
      {
        path: '', component: WelcomePageComponent, children: [
          {path: '', component: LoginComponent},
          {path: 'registration', component: RegistrationComponent},
          {path: 'changepass', component: ChangePasswordPageComponent},
        ]
      },
    ]),
    ReactiveFormsModule,
    SharedModule,
    MatCheckboxModule,
    MatSelectModule,
  ],
  exports: [
    RouterModule
  ],
  declarations: [
    WelcomePageComponent,
    LoginComponent,
    RegistrationComponent,
    PasswordRecoveryComponent,
    ChangePasswordPageComponent,
    AcceptMailPageComponent
  ],
  entryComponents: [LoginComponent, PasswordRecoveryComponent]
})
export class WelcomeModule {
}
