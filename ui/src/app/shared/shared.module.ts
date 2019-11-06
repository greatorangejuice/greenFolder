import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {DropdownDirective} from './directives/dropdown.directive';
import {
  _MatMenuDirectivesModule,
  MatButtonModule,
  MatIconModule,
  MatInputModule,
  MatMenuModule,
  MatProgressSpinnerModule,
  MatTableModule,
  MatButtonToggleModule,
  MatSnackBarModule,
  MatDialog,
  MatDialogModule,
  MatDatepickerModule, MatNativeDateModule, MatListModule,
} from '@angular/material';
import {MainLayoutComponent} from './components/main-layout/main-layout.component';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {SearchPipe} from './pipes/search.pipe';
import {GreencolorDirective} from "./directives/greencolor.directive";
import { AlertComponent } from './components/alert/alert.component';
import { LoaderComponent } from './components/loader/loader.component';
import { GoogleLoaderComponent } from './components/google-loader/google-loader.component';
import { SmallProfileComponent } from './components/small-profile/small-profile.component';

@NgModule({
  declarations: [
    DropdownDirective,
    GreencolorDirective,
    MainLayoutComponent,
    SearchPipe,
    AlertComponent,
    LoaderComponent,
    GoogleLoaderComponent,
    SmallProfileComponent,
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
    MatButtonModule,
    MatButtonToggleModule,
    MatTableModule,
    MatSnackBarModule,
    MatDialogModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatDialogModule,
    MatListModule
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
    MatButtonModule,
    MatButtonToggleModule,
    MatTableModule,
    SearchPipe,
    AlertComponent,
    MatDialogModule,
    LoaderComponent,
    MatDatepickerModule,
    MatNativeDateModule,
    MatDialogModule,
    MatListModule,
    GoogleLoaderComponent,
    GreencolorDirective
  ],
})
export class SharedModule {}
