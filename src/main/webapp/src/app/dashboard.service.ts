import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import {ErrMessageService} from "./err-message.service";


@Injectable({
  providedIn: 'root'
})
export class DashboardService {
  private url= "/Session";
  constructor(
    private http: HttpClient,
    private errMessage: ErrMessageService
  ) { }
  getSession (): Observable<any>{
    return this.http.get(this.url);
  }
  private handleError<T> (operation = 'operation', result?: T) {
    return (erreur: any): Observable<T> => {
      // log to console instead
      // TODO: better job of transforming error for user consumption
      this.log(`${erreur.message}`);

      console.log(`${erreur.message}`);
      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
  private log(message: String) {
    this.errMessage.SetMessage(message);
  }

}

