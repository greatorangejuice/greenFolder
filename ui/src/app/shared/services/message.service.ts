import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Dialog} from "../_models/dialog";
import {environment} from "../../../environments/environment";
import {EMPTY, Observable} from "rxjs";
import {delayedRetry} from "../customOperators/retryFailedRequest";
import {catchError} from "rxjs/operators";

@Injectable()
export class MessageService {
  constructor(
    private http: HttpClient,
  ){}

  getDialogs():Observable<Dialog[]> {
    return this.http.post<Dialog[]>(`${environment.backend}/dialogs`, {})
      .pipe(
        delayedRetry(1000, 3),
        catchError(error => {
          console.log(error);
          return EMPTY
        })
      )
  }

}
