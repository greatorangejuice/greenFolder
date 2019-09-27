import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {ActivatedRoute, Router} from '@angular/router';
import {AlertService} from "../../services/alert.service";

@Component({
  selector: 'app-main-layout',
  templateUrl: './main-layout.component.html',
  styleUrls: ['./main-layout.component.scss']
})
export class MainLayoutComponent implements OnInit {

  title =  '';

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
    // this.route.parent.url.subscribe((urlPath) => {
    //   // console.log(urlPath[0].path);
    //   const currentUrl = this.router.url;
    //   const currentParentUrl = urlPath[0].path;
    //   if (currentParentUrl !== 'tasks') {
    //     this.title = ' / Account';
    //     this.title = currentUrl;
    //   }
    // });
  }

  logout() {
    this.authService.logout();
    this.alertService.success('unlogin');
  }

  goToAccount() {
    this.router.navigate(['/user']);
  }

  goToMain() {
    this.router.navigate(['/tasks']);
  }
}
