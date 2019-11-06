import {AfterViewInit, Component, Input, OnInit} from '@angular/core';
import {AdminService} from "../../shared/services/admin.service";
import {User} from "../../shared/interfaces";
import {MatPaginator, MatTableDataSource} from "@angular/material";
import {FormControl} from "@angular/forms";

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
  ];

  filters = new FormControl();

  constructor(
    private adminService: AdminService,
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

}
