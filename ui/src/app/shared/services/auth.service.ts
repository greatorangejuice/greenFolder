import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {loginResponse, Univers, User} from '../interfaces';
import {BehaviorSubject, Observable} from 'rxjs';
import {environment} from '../../../environments/environment';
import {map, tap} from 'rxjs/operators';
import {JwtHelperService} from "@auth0/angular-jwt";
import {delayedRetry} from "../customOperators/retryFailedRequest";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private currentUserSubject: BehaviorSubject<User>;
  public currentUser: Observable<User>;

  constructor(
    private httpClient: HttpClient,
  ) {
    this.currentUserSubject = new BehaviorSubject<any>(localStorage.getItem('username'));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): User {
    return this.currentUserSubject.value;
  }

  permissions() {
    const myRawToken = localStorage.getItem('idToken');
    const helper = new JwtHelperService();
    const decodedToken = helper.decodeToken(myRawToken);
    console.log(decodedToken);
    // const expirationDate = helper.getTokenExpirationDate(myRawToken);
    // const isExpired = helper.isTokenExpired(myRawToken);
   if (decodedToken) {
     return decodedToken.roles
   }
   return [];
  }

  testPermissions() {
    const myRawToken = localStorage.getItem('idToken');
    const helper = new JwtHelperService();
    const isExpired = helper.isTokenExpired(myRawToken);
    return isExpired;
  }

  get token(): string {
    console.log('Проверка токена');
    const expireDate = new Date(localStorage.getItem('idToken-expires'));

    if (new Date() > expireDate) {
      console.log('Зашло в условие new Date > expireDate');
      this.logout();
      return
      // this.setToken(null);
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
      const expiresDate = new Date(new Date().getTime() + +response.tokenLifeTime);
      localStorage.setItem('idToken', response.token);
      localStorage.setItem('idToken-expires', expiresDate.toString());
    } else {
      localStorage.clear();
    }
  }

  refreshToken(): Observable<any> {
    console.log('REFRESH TOKEN');
    const token = localStorage.getItem('idToken');
    const username = localStorage.getItem('username');
    const request = { token: token, username: username };
    return this.httpClient.post(`${environment.backend}/token/refresh`, request);
  }

  // login(user: User): Observable<any> {
  //   return this.httpClient.post(`${environment.backend}/login`, user)
  //     .pipe(
  //       tap(
  //           this.setToken,
  //       )
  //     );
  // }

  login(user: User): Observable<any> {
    return this.httpClient.post(`${environment.backend}/login`, user)
      .pipe(
        tap(
          (user) => {
            localStorage.setItem('idToken', user.token);
            const expiresDate = new Date(new Date().getTime() + +user.tokenLifeTime);
            localStorage.setItem('idToken-expires', expiresDate.toString());
            this.currentUserSubject.next(user);
          }
        )
      );
  }

  logout() {
    localStorage.clear();
    this.currentUserSubject.next(null);
  }

  // logout() {
  //   this.setToken(null);
  // }

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
        delayedRetry(1000, 3),
        map(
          (res: Univers) => {
              return Object.keys(res.allUniversityAsMap)
          }
        ),
      )
  }

}



// console.log(expireDate);
// console.log('======================');
// const myRawToken = localStorage.getItem('idToken');
// const helper = new JwtHelperService();
// const decodedToken = helper.decodeToken(myRawToken);
// const expirationDate = helper.getTokenExpirationDate(myRawToken);
// const isExpired = helper.isTokenExpired(myRawToken);
// console.log(decodedToken);
// console.log(expireDate);
// console.log(isExpired);
// console.log(expirationDate);
// console.log('======================');
