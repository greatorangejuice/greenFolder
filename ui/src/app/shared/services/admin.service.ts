import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../../environments/environment";
import {tap} from "rxjs/operators";

@Injectable()
export class AdminService {
  constructor(
    private http: HttpClient,
  ) {}
  getAllUsers():Observable<any> {
    return this.http.get<any>(`${environment.backend}/admin/all-users`)
      .pipe(
        tap(
          (req) => {
            console.log(req);
          }
        )
      )
  }
}

