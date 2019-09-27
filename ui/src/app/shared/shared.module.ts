import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {DropdownDirective} from './directives/dropdown.directive';
import {
  _MatMenuDirectivesModule,
  MatButtonModule,
  MatIconModule,
  MatInputModule,
  MatMenuModule,
  MatProgressSpinnerModule
} from '@angular/material';
import {MainLayoutComponent} from './components/main-layout/main-layout.component';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {SearchPipe} from './pipes/search.pipe';
import {GreencolorDirective} from "./directives/greencolor.directive";
import { AlertComponent } from './components/alert/alert.component';

@NgModule({
  declarations: [
    DropdownDirective,
    MainLayoutComponent,
    SearchPipe,
    GreencolorDirective,
    AlertComponent,
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    _MatMenuDirectivesModule,
    MatButtonModule,
    MatIconModule,
    MatInputModule,
    MatMenuModule,
    MatProgressSpinnerModule,
    RouterModule,
  ],
  exports: [
    HttpClientModule,
    DropdownDirective,
    _MatMenuDirectivesModule,
    MatButtonModule,
    MatIconModule,
    MatInputModule,
    MatMenuModule,
    MatProgressSpinnerModule,
    MainLayoutComponent,
    RouterModule,
    SearchPipe,
  ],
})
export class SharedModule {}
