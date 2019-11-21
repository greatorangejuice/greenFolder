import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Dialog} from "../_models/dialog";
import {environment} from "../../../environments/environment";
import {EMPTY, Observable} from "rxjs";
import {delayedRetry} from "../customOperators/retryFailedRequest";
import {catchError, tap} from "rxjs/operators";

@Injectable()
export class MessageService {
  constructor(
    private http: HttpClient,
  ){}

  username = localStorage.getItem('username');

  getDialogs():Observable<Dialog[]> {
    return this.http.get<Dialog[]>(`${environment.backend}/message/all-dialogs/${this.username}`)
      .pipe(
        delayedRetry(1000, 3),
        catchError(error => {
          console.log(error);
          return EMPTY
        },
        )
      )
  }

  getPersonalDialog(secondUser):Observable<Dialog> {
    const responseBody = {firstUsername: this.username, secondUsername: secondUser};
    return this.http.post<Dialog>(`${environment.backend}/message/dialog`, responseBody)
      .pipe(
        tap(
          (req) => {
            console.log(req);
          }
        )
      )
  }

}
