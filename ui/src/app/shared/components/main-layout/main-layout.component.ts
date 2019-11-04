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
  isLoggedIn$: Observable<boolean>;
  isLogged = false;
  constructor(
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute,
    private alertService: AlertService,
  ) { }

  ngOnInit() {
    // this.isLoggedIn$ = this.authService.isLoggedIn();
    // console.log('logged?');
    // console.log(this.isLoggedIn$);
    if (this.router.url === '/tasks') {
      this.title = '';
    } else if(this.router.url === '/user') {
      this.title = ' / Account';
    }
    console.log(this.isLogged);
    this.isLogged = !this.authService.testPermissions();
    console.log(this.isLogged);

    // this.route.parent.url.subscribe((urlPath) => {
    //   // console.log(urlPath[0].path);
    //   const currentUrl = this.router.url;
    //   const currentParentUrl = urlPath[0].path;
    //   if (currentParentUrl !== 'tasks') {
    //     this.title = ' / Account';
    //     this.title = currentUrl;
    //   }
    // });
    this.roles = this.authService.permissions();
    console.log(this.roles);
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
