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

    const myRawToken = localStorage.getItem('idToken');
    if (!myRawToken) {
      this.router.navigate(['/welcome'], {
        queryParams: {
          loginAgain: true,
        }
      });
        return false;
    }
    const currentUser = this.authService.currentUserValue;

    const helper = new JwtHelperService();
    const decodedToken: Permissions = helper.decodeToken(myRawToken);
    // console.log(decodedToken.roles);

    const contains = (where, what) => {
      console.log('Зашло в contains');
      for(let i=0; i<what.length; i++){
        if(where.indexOf(what[i].toUpperCase()) == -1) return false;
      }
      return true;
    };

    if (currentUser && helper.isTokenExpired()) {
      // console.log('Роли пользователя:',  decodedToken.roles);
      // console.log('Роли гварда:', route.data.roles);
      if (route.data.roles && contains(decodedToken.roles, route.data.roles)) {
        console.log('Вернуло true');
        return true
      }

      if (helper.isTokenExpired() && route.data.roles && route.data.roles.indexOf(currentUser.role) === -1) {
        console.log('Доступ закрыт');
        this.router.navigate(['/welcome']);
        return false;
      }
      return true;
    }

    this.router.navigate(['/welcome'], {
      queryParams: {
        loginAgain: true,
      }
    });
    return false;

  }

}
