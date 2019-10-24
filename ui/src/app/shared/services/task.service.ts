import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {Task} from '../interfaces';
import {Observable} from "rxjs";

@Injectable()
export class TaskService {
  constructor(
    private http: HttpClient,
  ) {}
  createTask(task: Task) {
    return this.http.post(`${environment.fbDataBaseUrl}/tasks.json`, task);
  }

  getAllTasks(): Observable<Task[]> {
    return this.http.get<Task[]>(`${environment.backend}/task/active`);
  }

  getTaskById(id: string): Observable<Task> {
      return this.http.get<Task>(`${environment.backend}/task/${id}`)
  }

  createOrder(order: Task): Observable<any> {
    return this.http.post(`${environment.backend}/task`, order);
  }


}
