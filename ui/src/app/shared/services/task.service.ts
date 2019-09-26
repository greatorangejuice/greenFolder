import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {Task} from '../interfaces';
// import {AngularFireDataBase}

@Injectable()
export class TaskService {
  constructor(
    private http: HttpClient,
  ) {}
  createTask(task: Task) {
    return this.http.post(`${environment.fbDataBaseUrl}/tasks.json`, task);
  }

}
