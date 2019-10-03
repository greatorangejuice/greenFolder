import {Injectable} from '@angular/core';
import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {AuthService} from './services/auth.service';
import {Router} from '@angular/router';
import {catchError, tap} from 'rxjs/operators';
import {AlertService} from "./services/alert.service";

@Injectable()
export class AuthInterceptor implements HttpInterceptor{
  constructor(
    private auth: AuthService,
    private router: Router,
    private alertService: AlertService,
  ) {}
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    const idToken = localStorage.getItem('idToken');

    if (this.auth.isAuthenticated()) {
      req = req.clone({
        headers: req.headers.set("Authorization",
          "Bearer_ " + idToken),
      });
    }

    return next.handle(req)
      .pipe(
        catchError((error: HttpErrorResponse) => {
          if (error.status === 401) {
            console.log(401);
            // this.auth.logout();
            // this.router.navigate(['/welcome'], {
            //   queryParams: {
            //     authfailed: true
            //   }
            // });
          } else if(error.status === 403) {
            console.log(403);
          }
          this.alertService.warning('test warning');
          return throwError(error)
        })
      )
  }

}
