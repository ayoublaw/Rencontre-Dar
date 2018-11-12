import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import { Observable, of } from 'rxjs';

import {ErrMessageService} from './err-message.service';
import {Data, Router} from '@angular/router';

const httpOptions = {
  headers: new HttpHeaders(
    {'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private urlLogin = 'http://localhost:5000/login';
  private urlRegister = 'http://localhost:5000/register';
  constructor(
    private http: HttpClient,
    private errMessage: ErrMessageService,
    private router : Router) {}
  login(email: String, password: String) {
    return this.http.post(this.urlLogin, { email, password } , httpOptions)
      .pipe(
        catchError(this.handleError('login', []))
      ).subscribe(data => {
          this.router.navigate(['/dashboard']);
        },
        error => {
          console.log('Error', error);
        });
    }
  register(email: String, password: String, nom: String, prenom: String, age: String, sex: String, centreInt: String[]) {return this.http.post(this.urlRegister, { email: email, password: password, nom: nom, prenom: prenom, age: age, sex: sex, centreInt: centreInt} , httpOptions)
      .pipe(catchError(this.handleError('register', [])))
      .subscribe(data => {
          this.router.navigate(['/dashboard']);
        },
        error => {
          console.log('Error', error);
        });
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
