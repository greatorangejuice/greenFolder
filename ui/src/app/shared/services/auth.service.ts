import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {loginResponse, Token, Univers, User} from '../interfaces';
import {BehaviorSubject, Observable} from 'rxjs';
import {environment} from '../../../environments/environment';
import {map, tap} from 'rxjs/operators';
import {Router} from '@angular/router';
import {AlertService} from "./alert.service";
import { JwtHelperService } from "@auth0/angular-jwt";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private httpClient: HttpClient,
    private route: Router,
    private alertService: AlertService,
  ) {}

  permissions() {
    const myRawToken = localStorage.getItem('idToken');
    const helper = new JwtHelperService();
    const decodedToken = helper.decodeToken(myRawToken);
    // const expirationDate = helper.getTokenExpirationDate(myRawToken);
    // const isExpired = helper.isTokenExpired(myRawToken);
    return decodedToken.roles;
  }

  get token(): string {
    console.log('Запрос на get token');
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

  isAuthenticated(): boolean {
    return !!this.token;
  }

  private setToken(response: loginResponse | null) {
    console.log('Устанавливаю токен');
    console.log(response);
    if (response) {
      const expiresDate = new Date(new Date().getTime() + +response.tokenLifeTime * 1000);
      localStorage.setItem('idToken', response.token);
      localStorage.setItem('idToken-expires', expiresDate.toString());
    } else {
      localStorage.clear();
    }
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
  }

  signup(user: User): Observable<any> {
    return this.httpClient.post(`${environment.backend}/registration`, user);
  }

  confirmEmail(key: string): Observable<any> {
    return this.httpClient.get(`${environment.backend}/activation/${key}`)
  }

  restorePassword(email: string): Observable<any> {
    return this.httpClient.post(`${environment.backend}/accessrestore`, {email: email});
  }

  checkLink(recoveryKey: string): Observable<any> {
    return this.httpClient.get(`${environment.backend}/accessrestore/${recoveryKey}`);
  }

  setNewPassword(password: string, recoveryKey: string): Observable<any> {
    return this.httpClient.post(`${environment.backend}/accessrestore/${recoveryKey}`, {newPassword: password});
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
