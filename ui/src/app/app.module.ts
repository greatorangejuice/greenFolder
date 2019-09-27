import { BrowserModule } from '@angular/platform-browser';
import {NgModule, Provider} from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {WelcomeModule} from './welcome-page/welcome.module';
import {TaskModule} from './task-page/task.module';
import {SharedModule} from './shared/shared.module';
import {AuthGuard} from './shared/services/auth.guard';
import {UserModule} from './user-page/user.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {HTTP_INTERCEPTORS} from '@angular/common/http';
import {AuthInterceptor} from './shared/auth.interceptor';
import {AlertService} from "./shared/services/alert.service";

const INTERCEPTOR: Provider = {
  provide: HTTP_INTERCEPTORS,
  multi: true,
  useClass: AuthInterceptor,
};

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    WelcomeModule,
    TaskModule,
    SharedModule,
    UserModule,
    BrowserAnimationsModule,
  ],
  providers: [AuthGuard, INTERCEPTOR, AlertService],
  bootstrap: [AppComponent]
})
export class AppModule { }
