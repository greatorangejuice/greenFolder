import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {EMPTY, Observable, of, throwError} from "rxjs";
import {environment} from "../../../environments/environment";
import {AllAccountInfo, Passwords} from "../interfaces";
import {catchError, tap} from "rxjs/operators";
import {delayedRetry} from "../customOperators/retryFailedRequest";

@Injectable()
export class UserService {


  constructor(
    private http: HttpClient,
  ) {}

  editProfile(passwords: Passwords):Observable<any> {
    return this.http.put(`${environment.backend}/edit`, passwords);
  }

  getProfile():Observable<AllAccountInfo> {

    const username = localStorage.getItem('username');
    return this.http.get<AllAccountInfo>(`${environment.backend}/profile/username/${username}`)
      .pipe(
        // delayedRetry(1000, 3),
        catchError(error => {
          console.log(error);
          return EMPTY;
          }),
        tap(
          (res) => {
            console.log(res);
          }
        ),
      )
  }
}
