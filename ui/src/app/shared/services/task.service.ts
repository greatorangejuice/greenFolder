import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {CustomerResponse, Offer, Task} from '../interfaces';
import {Observable} from "rxjs";

@Injectable()
export class TaskService {
  constructor(
    private http: HttpClient,
  ) {}

  getAllTasks(): Observable<Task[]> {
    return this.http.get<Task[]>(`${environment.backend}/task/active`);
  }

  getTaskById(id: string): Observable<Task> {
      return this.http.get<Task>(`${environment.backend}/task/${id}`)
  }

  createTask(task: Task): Observable<Task> {
    return this.http.post<Task>(`${environment.backend}/task`, task);
  }

  createOrder(offer: Offer): Observable<any> {
    return this.http.post(`${environment.backend}/offer`, offer);
  }

  submitCustomerResponse(response): Observable<any> {
    return this.http.post(`${environment.backend}/offer/response`, response);
  }

}
