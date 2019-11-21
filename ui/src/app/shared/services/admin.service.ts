import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../../environments/environment";
import {tap} from "rxjs/operators";
import {User} from "../interfaces";

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

  editUser(user: User):Observable<void> {
    return this.http.put<void>(`${environment.backend}/edit-user`, user)
  }

  banUser(username: string):Observable<void> {
    return this.http.get<void>(`${environment.backend}/admin/user/ban/${username}`)
  }

  unbanUser(username: string):Observable<void> {
    return this.http.get<void>(`${environment.backend}/admin/user/unban/${username}`)
  }

  deactivateUser(username: string):Observable<void> {
    return this.http.get<void>(`${environment.backend}/admin/user/disable/${username}`)
  }

  activateUser(username: string):Observable<void> {
    return this.http.get<void>(`${environment.backend}/admin/user/restore/${username}`)
  }

  setRoles(username:string, role: string[]):Observable<void> {
    return this.http.post<void>(`${environment.backend}/admin/user/set-roles/${username}`, role)
  }

  deleteRoles(username:string, role: string[]):Observable<void> {
    return this.http.post<void>(`${environment.backend}/admin/user/delete-roles/${username}`, role)
  }

}
