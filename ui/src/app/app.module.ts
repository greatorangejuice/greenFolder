import { BrowserModule } from '@angular/platform-browser';
import {NgModule, Provider} from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import {AuthGuard} from './shared/services/auth.guard';
import {AuthInterceptor} from './shared/auth.interceptor';
import {HTTP_INTERCEPTORS} from '@angular/common/http';

import { AppComponent } from './app.component';
import {WelcomeModule} from './welcome-page/welcome.module';
import {TaskModule} from './task-page/task.module';
import {SharedModule} from './shared/shared.module';
import {UserModule} from './user-page/user.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {AlertService} from "./shared/services/alert.service";
import {MainPageModule} from "./main-page/main-page.module";
import {CatchErrorInterceptor} from "./shared/interceptors/catch-error.interceptor";
import { DialogPageComponent } from './dialog-page/dialog-page.component';
import {DialogPageModule} from "./dialog-page/dialog-page.module";
import {registerLocaleData} from "@angular/common";
import rulocale from "@angular/common/locales/ru";



registerLocaleData(rulocale, 'ru');

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
    MainPageModule,
    DialogPageModule,
  ],
  providers: [
    AuthGuard,
    {provide: HTTP_INTERCEPTORS, multi: true, useClass: AuthInterceptor},
    {provide: HTTP_INTERCEPTORS, multi: true, useClass: CatchErrorInterceptor},
    AlertService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
