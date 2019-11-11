import {Component, Inject, OnInit} from '@angular/core';
import {AdminService} from "../../shared/services/admin.service";
import {User} from "../../shared/interfaces";
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef, MatPaginator} from "@angular/material";
import {FormControl} from "@angular/forms";
import {Role} from "../../shared/_models/role";

export class EditUserData {
  username: string;
  roles: Role;
}

@Component({
  selector: 'app-all-users',
  templateUrl: './all-users.component.html',
  styleUrls: ['./all-users.component.scss']
})
export class AllUsersComponent implements OnInit {

  users: User[];
  errorMessage = '';
  displayedColumns = [
    'id',
    'username',
    'email',
    'status',
    'actions',
  ];
  paginator: MatPaginator;
  dataSource: User[];
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
    this.adminService.getAllUsers()
      .subscribe(
        (users) => {
          this.dataSource = users;
        },
      (error) => {
          if (error.status === 403) {
            this.errorMessage = 'Access запрещен'
          } else {
            this.errorMessage = 'Ошибка'
          }
      }
      );
  }

  openUserEditor(username, roles, status): void {
    const dialogRef = this.dialog.open(EditUserModal, {
      width: '250px',
      data: {username, roles, status}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

}


@Component({
  selector: 'edit-user-modal',
  templateUrl: 'edit-user-modal.html',
})
export class EditUserModal implements OnInit{

  constructor(
    public dialogRef: MatDialogRef<EditUserModal>,
    @Inject(MAT_DIALOG_DATA) public data: User) {}

  closeUserEditor(): void {
    this.dialogRef.close();
  }

  ngOnInit(): void {
    console.log(this.data);
  }

}
