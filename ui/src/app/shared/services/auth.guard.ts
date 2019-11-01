import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';
import {AuthService} from './auth.service';
import {JwtHelperService} from "@auth0/angular-jwt";
import {Permissions} from "../interfaces";

@Injectable()
export class AuthGuard implements CanActivate {
  constructor(
    private authService: AuthService,
    private router: Router,
  ) {}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    console.log('Заходит в Guard');

    const currentUser = this.authService.currentUserValue;
    console.log(currentUser);
    const myRawToken = localStorage.getItem('idToken');
    const helper = new JwtHelperService();
    const decodedToken: Permissions = helper.decodeToken(myRawToken);

    if (currentUser && helper.isTokenExpired()) {
      if (route.data.roles && route.data.roles.indexOf(currentUser.role) === -1) {
        this.router.navigate(['/']);
        return false;
      }
      return true;
    }

    // permissions() {
    //   const myRawToken = localStorage.getItem('idToken');
    //   const helper = new JwtHelperService();
    //   const decodedToken = helper.decodeToken(myRawToken);
    //   console.log(decodedToken);
    //   // const expirationDate = helper.getTokenExpirationDate(myRawToken);
    //   // const isExpired = helper.isTokenExpired(myRawToken);
    //   return decodedToken.roles;
    // }


    if (this.authService.isAuthenticated()) {
      return true;
    } else {
      this.authService.logout();
      this.router.navigate(['/welcome'], {
        queryParams: {
          loginAgain: true,
        }
      });
    }
  }

}
