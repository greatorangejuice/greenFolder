import { Component, OnInit } from '@angular/core';
import {MatDialog} from "@angular/material";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../shared/services/user.service";
import {EditProfileComponent} from "./edit-profile/edit-profile.component";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  constructor(
    private userService: UserService,
    public dialog: MatDialog,
  ) {}

  openDialog() {
    const dialogRef = this.dialog.open(EditProfileComponent);

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }
  ngOnInit() {
    this.userService.getProfile()
      .subscribe()
  }

}


// @Component({
//   selector: 'edit-profile-modal',
//   templateUrl: 'edit-profile-modal.html',
//   styleUrls: ['./profile.component.scss']
// })
// export class EditProfileModal implements OnInit{
//   form: FormGroup;
//   namePlaceholder = 'Custom Username';
//   closeValue = 'Cancel' || 'Exit';
//
//   constructor(
//     private userService: UserService,
//   ) {}
//
//   ngOnInit() {
//     this.form = new FormGroup(
//       {
//         username: new FormControl({value: '', disabled: true}, ),
//       },
//     )
//   }
//
//   editProfile() {
//     this.userService.editProfile()
//       .subscribe(
//
//       );
//     console.log('editted');
//   }
// }
