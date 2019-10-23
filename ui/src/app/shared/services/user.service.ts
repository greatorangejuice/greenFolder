import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../../environments/environment";
import {AllAccountInfo, Passwords} from "../interfaces";
import {tap} from "rxjs/operators";
import {All} from "tslint/lib/rules/completedDocsRule";

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
        tap(
          (res) => {
            console.log(res);
          }
        )
      )
  }
}
