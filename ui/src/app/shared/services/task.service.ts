import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {Task, Users} from '../interfaces';
import {Observable} from "rxjs";
import {map, tap} from "rxjs/operators";
// import {AngularFireDataBase}

@Injectable()
export class TaskService {
  constructor(
    private http: HttpClient,
  ) {}
  createTask(task: Task) {
    return this.http.post(`${environment.fbDataBaseUrl}/tasks.json`, task);
  }

  getTaskById(): Observable<any> {
    return this.http.get<Users>(`${environment.fbDataBaseUrl}/users.json`)
      .pipe(
        map((response: {[key: string]:any}) => {
          return Object
            .keys(response)
            .map(key => ({
              ...response[key]
            }))
        })
      )
  }

}
