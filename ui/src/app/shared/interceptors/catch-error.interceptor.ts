import {Injectable} from "@angular/core";
import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {catchError, tap} from "rxjs/operators";
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";
import {AlertService} from "../services/alert.service";

@Injectable()
export class CatchErrorInterceptor implements HttpInterceptor{

  constructor(
    private auth: AuthService,
    private router: Router,
    private alertService: AlertService,
  ) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(
      tap(
        (err) => {
          console.log('ErrorInterceptor')
          console.log(err);
        }
      ),
      catchError((error: HttpErrorResponse) => {
        switch (error.status) {
          case 400:
            this.alertService.warning('Bad Request');
            break;
          case 401:
            this.alertService.warning('Unauthorized');
            this.auth.logout();
            this.router.navigate(['/welcome'], {
              queryParams: {
                authfailed: true
              }
            });
            break;
          case 403:
            this.alertService.warning('Access denied');
            break;
          case 404:
            this.alertService.warning('Not found');
            break;
          default:
            this.alertService.warning('Something wrong');
        }
        return throwError(error)
      })
    )
  }
}
