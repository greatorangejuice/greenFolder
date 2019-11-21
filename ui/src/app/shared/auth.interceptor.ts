import {Injectable} from '@angular/core';
import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {AuthService} from './services/auth.service';
import {Router} from '@angular/router';
import {AlertService} from "./services/alert.service";
import {root} from "rxjs/internal-compatibility";

@Injectable({
  providedIn: "root",
})
export class AuthInterceptor implements HttpInterceptor{
  constructor(
    private auth: AuthService,
    private router: Router,
    private alertService: AlertService,
  ) {}
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    const idToken = localStorage.getItem('idToken');
    console.log(idToken);
    if (this.auth.isAuthenticated()) {
      const cloned = req.clone(
        {
          setHeaders: {
            Authorization: `Bearer_${idToken}`
          }
        }
      );
      return next.handle(cloned);
    } else {
      return next.handle(req)
    }
  }

}
