import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../../environments/environment";
import {Passwords} from "../interfaces";
import {tap} from "rxjs/operators";

@Injectable()
export class UserService {
  constructor(
    private http: HttpClient,
  ) {}

  editProfile(passwords: Passwords):Observable<any> {
    return this.http.put(`${environment.backend}/edit`, passwords);
  }

  getProfile():Observable<any> {
    return this.http.get(`${environment.backend}/profile/1`, {headers: {'token': 'toooooken'}})
      .pipe(
        tap(
          (res) => {
            console.log(res);
          }
        )
      )
  }
}
