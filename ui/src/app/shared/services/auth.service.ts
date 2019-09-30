import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {FbAuthResponse, User} from '../interfaces';
import {Observable} from 'rxjs';
import {environment} from '../../../environments/environment';
import {map, tap} from 'rxjs/operators';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  userId: string;

  constructor(
    private httpClient: HttpClient,
    private route: Router,
  ) {}

  get token(): string {
    const expireDate = new Date(localStorage.getItem('fb-token-expires'));
    if (new Date() > expireDate) {
      this.logout();
      return null;
    }
    return localStorage.getItem('fb-token');
  }

  // getId(req: any): Observable<any> {
    // console.log(this.userId);
    // return this.userId;
    // console.log('getid: ', req);
    // return this.lo
  // }

  testGetUsers():Observable<any> {
    return this.httpClient.get('http://localhost:8080/user/all');
  }


  login(user: User): Observable<any> {
    // user.returnSecureToken = true;
    return this.httpClient.post(`https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=${environment.apiKey}`, user)
      .pipe(
        tap(
            // console.log(v);
            this.setToken,
        )
      );
  }

  getUserData(user: User): Observable<any> {
    user.returnSecureToken = true;
    return this.httpClient.post(`https://identitytoolkit.googleapis.com/v1/accounts:lookup?key=${environment.apiKey}`,
      {idToken: 'IISu6yhZybYSHaGzl1ZEPXWM1Fo1'})
      .pipe(
        tap( req => console.log(req),
            err => console.log(err)
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
    user.returnSecureToken = true;
    return this.httpClient.post(`https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=${environment.apiKey}`, user);
  }

  sendUserData(userInfo): Observable<any> {
    return this.httpClient.post(`${environment.fbDataBaseUrl}/users.json`, userInfo);
  }

  isAuthenticated(): boolean {
    return !!this.token;
  }


  // Получаем токен и закидыаем его в локал сторедж;
  private setToken(response: FbAuthResponse | null) {
    if (response) {
      this.userId = response.localId;
      console.log(this.userId);
      const expiresDate = new Date(new Date().getTime() + +response.expiresIn * 1000);
      localStorage.setItem('fb-token', response.idToken);
      localStorage.setItem('fb-token-expires', expiresDate.toString());
    } else {
      localStorage.clear();
    }
  }




}
