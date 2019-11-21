import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {EMPTY, Observable, of, throwError} from "rxjs";
import {environment} from "../../../environments/environment";
import {AllAccountInfo, Offer, Passwords} from "../interfaces";
import {catchError, tap} from "rxjs/operators";
import {delayedRetry} from "../customOperators/retryFailedRequest";

@Injectable()
export class UserService {

  username = localStorage.getItem('username');

  constructor(
    private http: HttpClient,
  ) {}

  editProfile(passwords: Passwords):Observable<any> {
    return this.http.put(`${environment.backend}/edit`, passwords);
  }

  getProfile():Observable<AllAccountInfo> {

    return this.http.get<AllAccountInfo>(`${environment.backend}/profile/username/${this.username}`)
      .pipe(
        delayedRetry(1000, 3),
        catchError(error => {
          console.log(error);
          return EMPTY;
          }),
      )
  }

  getOffersList():Observable<Offer[]> {
    return this.http.get<Offer[]>(`${environment.backend}/offer/executor/${this.username}/active`);
  }
}
