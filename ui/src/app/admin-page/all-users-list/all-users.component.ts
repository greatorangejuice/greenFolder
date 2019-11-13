import {Component, Inject, OnInit} from '@angular/core';
import {AdminService} from "../../shared/services/admin.service";
import {User} from "../../shared/interfaces";
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef, MatPaginator} from "@angular/material";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Role} from "../../shared/_models/role";
import {tap} from "rxjs/operators";
import {Observable} from "rxjs";

export class EditUserData {
  username: string;
  roles: Role;
  city: string;
}

@Component({
  selector: 'app-all-users',
  templateUrl: './all-users.component.html',
  styleUrls: ['./all-users.component.scss']
})
export class AllUsersComponent implements OnInit {

  usersStream$: Observable<User[]>;

  errorMessage = '';
  displayedColumns = [
    'id',
    'username',
    'email',
    'status',
    'actions',
  ];
  paginator: MatPaginator;
  // dataSource: User[];
  filterList = [
    'id',
    'username',
    'email',
    'status',
    'money',
    'roles',
    'university',
    'faculty',
    'course',
    'actions',
  ];

  filters = new FormControl();

  constructor(
    private adminService: AdminService,
    public dialog: MatDialog,
  ) { }

  ngOnInit() {
    this.usersStream$ = this.adminService.getAllUsers()
  }

  openUserEditor(username, role, userStatus, city, webMoneyAccount, faculty, id, university, name, surname): void {
    const dialogRef = this.dialog.open(EditUserModal, {
      width: '500px',
      data: {username, role, userStatus, city, webMoneyAccount, faculty, id, surname, university, name}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

}


@Component({
  selector: 'edit-user-modal',
  templateUrl: 'edit-user-modal.html',
  styleUrls: ['./all-users.component.scss']
})
export class EditUserModal implements OnInit{

  form: FormGroup;
  message = '';
  rolesList = ['USER', 'ADMIN', 'CUSTOMER', 'EXECUTOR'];

  constructor(
    private adminService: AdminService,
    public dialogRef: MatDialogRef<EditUserModal>,
    @Inject(MAT_DIALOG_DATA) public data) {}

  closeUserEditor(): void {
    this.dialogRef.close();
  }

  ngOnInit(): void {
    console.log(this.data);
    this.data.role.map((role) => { role.checked = this.rolesList.includes(role.name)});
    console.log(this.data.role);

    this.form = new FormGroup({
      username: new FormControl({value: this.data.username, disabled: true}),
      name: new FormControl(this.data.name, [Validators.required]),
      surname: new FormControl(this.data.surname, [Validators.required]),
      status: new FormControl(this.data.userStatus, [Validators.required]),
      // roles: new FormControl('', [Validators.required]),
    });

  }

  submit() {
    const changedUser = {
    username: this.data.username,
    email: this.data.email,
    name: this.form.value.name,
    surname: this.form.value.surname,
    city: this.data.city,
    university: this.data.university,
    faculty: this.data.faculty,
    course: this.data.course,
    userStatus: this.form.value.status,
    webMoneyAccount: this.data.webMoneyAccount,
    role: this.data.role,
    };

    this.adminService.editUser(changedUser)
      .subscribe(
        (req) => {
          console.log(req);
        }
      )
  }

  banUser(username: string) {
    this.adminService.banUser(username)
      .subscribe(
        () => {}
      )
  }

  unbanUser(username:string) {
    this.adminService.unbanUser(username)
      .subscribe()
  }

}
