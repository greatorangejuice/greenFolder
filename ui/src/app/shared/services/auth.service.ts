import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {FbAuthResponse, User} from '../interfaces';
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

  userId: string;

  constructor(
    private httpClient: HttpClient,
    private route: Router,
    private alertService: AlertService,
  ) {}

  get token(): string {
    const expireDate = new Date(localStorage.getItem('fb-token-expires'));
    if (new Date() > expireDate) {
      this.logout();
      return null;
    }
    return localStorage.getItem('fb-token');
  }

  login(user: User): Observable<any> {
    return this.httpClient.post(`https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=${environment.apiKey}`, user)
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
    return this.httpClient.post(`${environment.backend}/register`, user);
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
      const expiresDate = new Date(new Date().getTime() + +response.expiresIn * 1000);
      localStorage.setItem('fb-token', response.idToken);
      localStorage.setItem('fb-token-expires', expiresDate.toString());
    } else {
      localStorage.clear();
    }
  }

  checkEmail(email: object): Observable<string> {
    return this.httpClient.post<string>(`${environment.backend}/mail/check`, email);
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
