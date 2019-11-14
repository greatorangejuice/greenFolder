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
  MatDatepickerModule, MatNativeDateModule, MatListModule, MatBadgeModule,
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
import { MessageListComponent } from './components/small-profile/message-list/message-list.component';
import {BoldDirective} from "./directives/bold.directive";
import { DialogListComponent } from './components/small-profile/dialog-list/dialog-list.component';

@NgModule({
  declarations: [
    DropdownDirective,
    GreencolorDirective,
    BoldDirective,
    MainLayoutComponent,
    SearchPipe,
    AlertComponent,
    LoaderComponent,
    GoogleLoaderComponent,
    SmallProfileComponent,
    MessageListComponent,
    DialogListComponent,
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
    MatListModule,
    MatBadgeModule
  ],
  exports: [
    HttpClientModule,
    DropdownDirective,
    BoldDirective,
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
