import {Component, EventEmitter, Inject, OnInit, Output} from '@angular/core';
import {AdminService} from "../../shared/services/admin.service";
import {User} from "../../shared/interfaces";
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef, MatPaginator} from "@angular/material";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Role} from "../../shared/_models/role";
import {switchMap, tap} from "rxjs/operators";
import {Observable, Subject} from "rxjs";

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

  users$ = new Subject<User[]>();
  users: User[];

  errorMessage = '';
  displayedColumns = [
    'id',
    'username',
    'email',
    'status',
    'actions',
  ];
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

  constructor(
    private adminService: AdminService,
    public dialog: MatDialog,
  ) { }

  ngOnInit() {

    this.adminService.getAllUsers().subscribe(
      users => this.users$.next(users)
    );

    // this.usersStream$ = this.adminService.getAllUsers()
  }

  openUserEditor(username, role, userStatus, city, webMoneyAccount, faculty, id, university, name, surname): void {
    const dialogRef = this.dialog.open(EditUserModal, {
      width: '500px',
      data: {username, role, userStatus, city, webMoneyAccount, faculty, id, surname, university, name}
    });

    dialogRef.componentInstance.userChanged.subscribe(
      result => {
        switch (result.action) {
          case 'ban':
            this.banUser(result.username);
            break;
          case "unban":
            this.unbanUser(result.username);
            break;
          case "deactivate":
            this.deactivateUser(result.username);
            break;
          case "activate":
            this.activateUser(result.username);
            break
        }

      }
    );

    // dialogRef.afterClosed().subscribe(result => {
    //
    //
    //   console.log('The dialog was closed');
    // });
  }

  banUser(username: string) {
    this.adminService.banUser(username)
      .pipe(switchMap(()=> this.adminService.getAllUsers()))
      .subscribe(users => this.users$.next(users))
  }

  unbanUser(username: string) {
    this.adminService.unbanUser(username)
      .pipe(switchMap(()=> this.adminService.getAllUsers()))
      .subscribe(users => this.users$.next(users))
  }

  deactivateUser(username: string) {
    this.adminService.deactivateUser(username)
      .pipe(switchMap(()=> this.adminService.getAllUsers()))
      .subscribe(users => this.users$.next(users))
  }

  activateUser(username: string) {
    this.adminService.activateUser(username)
      .pipe(switchMap(()=> this.adminService.getAllUsers()))
      .subscribe(users => this.users$.next(users))
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
  changedUser: any;
  loading = false;

  @Output() userChanged = new EventEmitter<any>();

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
    this.changedUser = {
      username: this.data.username,
      action: null,
    };

    // this.adminService.editUser(changedUser.data)
    //   .subscribe(
    //     (req) => {
    //       console.log(req);
    //     }
    //   )
  }

  banUser(username: string) {
    const result = {username: username, action: 'ban'};
    this.userChanged.emit(result);
    this.loading = true;
    setTimeout(
      () => {
        this.data.userStatus = 'BANNED';
        this.loading = false
      }, 1500
    )
  }

  unbanUser(username:string) {
    const result = {username: username, action: 'unban'};
    this.userChanged.emit(result);
    this.data.userStatus = 'ACTIVE';
  }

  deactivateUser(username: string) {
    const result = {username: username, action: 'deactivate'};
    this.userChanged.emit(result)
  }

  activateUser(username: string) {
    const result = {username: username, action: 'activate'};
    this.userChanged.emit(result)
  }



}


// username: this.data.username,
//   email: this.data.email,
//   name: this.form.value.name,
//   surname: this.form.value.surname,
//   city: this.data.city,
//   university: this.data.university,
//   faculty: this.data.faculty,
//   course: this.data.course,
//   userStatus: this.form.value.status,
//   webMoneyAccount: this.data.webMoneyAccount,
//   role: this.data.role,
