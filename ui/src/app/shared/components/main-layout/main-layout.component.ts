import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {ActivatedRoute, Router} from '@angular/router';
import {AlertService} from "../../services/alert.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-main-layout',
  templateUrl: './main-layout.component.html',
  styleUrls: ['./main-layout.component.scss']
})
export class MainLayoutComponent implements OnInit {

  title =  '';
  roles = [];
  isLogged = false;

  constructor(
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute,
    private alertService: AlertService,
  ) { }

  ngOnInit() {
    if (this.router.url === '/tasks') {
      this.title = '';
    } else if(this.router.url === '/user') {
      this.title = ' / Account';
    }
    this.isLogged = !this.authService.isExpired();
    this.roles = this.authService.permissions();
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/welcome']);
    this.alertService.success('unlogin');
  }

  goToPage(pageUrl) {
    this.router.navigate(([`/${pageUrl}`]));
  }
}
