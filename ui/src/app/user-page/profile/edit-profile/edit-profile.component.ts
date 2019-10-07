import { Component, OnInit } from '@angular/core';
import {FormGroup, Validators, FormBuilder} from "@angular/forms";
import {UserService} from "../../../shared/services/user.service";
import {MustMatch} from "../../../shared/forms-validators/same-fileds-values";

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.scss']
})
export class EditProfileComponent implements OnInit {

  form: FormGroup;
  namePlaceholder = 'Custom Username';
  closeValue = 'Cancel' || 'Exit';

  constructor(
    private userService: UserService,
    private formBuilder: FormBuilder,
  ) {}

  ngOnInit() {
    this.form = this.formBuilder.group({
      username: ['', [Validators.required, Validators.minLength(2)]],
      currentPassword: ['', [Validators.required]],
      newPassword: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', [Validators.required, Validators.minLength(6)]],
    }, {
      validator: MustMatch('newPassword', 'confirmPassword')
    });

  }

  editProfile() {
    const passwords = {
      currentPassword: this.form.value.currentPassword,
      newPassword: this.form.value.password,
    };
    this.userService.editProfile(passwords)
      .subscribe(

      );
  }

}
