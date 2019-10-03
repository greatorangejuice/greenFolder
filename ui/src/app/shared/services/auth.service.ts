import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {loginResponse, Token, User} from '../interfaces';
import {BehaviorSubject, Observable} from 'rxjs';
import {environment} from '../../../environments/environment';
import {map, tap} from 'rxjs/operators';
import {Router} from '@angular/router';
import {AlertService} from "./alert.service";

export interface Univers {
  allUniversityAsMap: object;
}
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private httpClient: HttpClient,
    private route: Router,
    private alertService: AlertService,
  ) {}

  get token(): string {
    const expireDate = new Date(localStorage.getItem('idToken-expires'));
    if (new Date() > expireDate) {
      this.logout();
      return null;
    }
    if (expireDate.getTime() - new Date().getTime() <= 300000) {
      this.refreshToken()
        .subscribe(
            this.setToken,
        )
    }
    return localStorage.getItem('idToken');
  }

  refreshToken(): Observable<any> {
    const token = localStorage.getItem('idToken');
    const username = localStorage.getItem('username');
    const request = { token: token, username: username };
    return this.httpClient.post(`${environment.backend}/token/refresh`, request);
  }

  login(user: User): Observable<any> {
    return this.httpClient.post(`${environment.backend}/login`, user)
      .pipe(
        tap(
            this.setToken,
        )
      );
  }

  logout() {
    this.setToken(null);
    this.route.navigate(['/welcome'], {
      queryParams: {
        logout: true,
      }
    });
  }

  signup(user: User): Observable<any> {
    return this.httpClient.post(`${environment.backend}/registration`, user);
  }

  isAuthenticated(): boolean {
    return !!this.token;
  }

  private setToken(response: loginResponse | null) {
    if (response) {
      const expiresDate = new Date(new Date().getTime() + +response.expiresIn * 1000);
      localStorage.setItem('idToken', response.idToken);
      localStorage.setItem('idToken-expires', expiresDate.toString());
    } else {
      localStorage.clear();
    }
  }

  getUniversityList():Observable<any> {
    return this.httpClient.get(`${environment.backend}/registration`)
      .pipe(
        map(
          (res: Univers) => {
              return Object.keys(res.allUniversityAsMap)
          }
        )
      )
  }

}



// checkEmail(email: object): Observable<string> {
//   return this.httpClient.post<string>(`${environment.backend}/mail/check`, email);
// }
