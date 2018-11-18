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
        tap(data =>console.log(data)),
        catchError(this.handleError('login', []))
      );
    }
  register(email: String, password: String, nom: String, prenom: String, age: String, sex: String, centreInt: String[]) {
    return this.http.post(this.urlRegister, { email: email, password: password, nom: nom, prenom: prenom, age: age, sex: sex, centreInt: centreInt} , httpOptions)
      .pipe(
        tap(data => console.log(data)),
        catchError(this.handleError('register', []))
      )
      ;
  }
  GetAllUsers(): Observable<any>{
    return this.http.get('/AllUsers')
      .pipe(
        tap(data => console.log(data)),
        catchError(this.handleError('Delete Evenement'))
      )
  }
  SignalCompte(nom: String,prenom: String,Description: String): Observable<any>{
    return this.http.post('SignalCompte',{nom:nom,prenom:prenom,description: Description})
      .pipe(
        tap(data => console.log(data)),
        catchError(this.handleError('Delete Evenement'))
      )
  }
  ListSignalCompte():Observable<any>{
    return this.http.get('ListSignalCompte')
      .pipe(
        tap(data =>console.log(data)),
        catchError(this.handleError('ListSignalCompte'))
      )
  }
  ConfirmSignalCompte(Email : String) : Observable<any>{
    return this.http.post('ConfirmSignalCompte',{Email : Email})
      .pipe(
        tap(data =>console.log(data)),
        catchError(this.handleError('ConfirmSignalCompte'))
      )
  }
  private handleError<T> (operation = 'operation', result?: T) {
    return (erreur: any): Observable<T> => {
      console.log(erreur);
      this.log(`${erreur.error.ERR}`);
      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
  private log(message: String) {
    this.errMessage.SetMessage(message);
  }

}
