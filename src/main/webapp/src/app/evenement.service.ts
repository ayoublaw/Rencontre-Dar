import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import { Observable, of } from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders(
    {'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})


export class EvenementService {
  public MessageErr;
  private urlDemande = "/GetPlaceNearby";
  private urlAdd = "/Addevent";
  private urlDir = "Directions";

  constructor(
    private http: HttpClient
  ) { }
  Addevent(description: String,nbrParticipants: String,date: String,CentreInt: String,lieu: String,adr_proposer: String): Observable<any>{
    return this.http.post(this.urlAdd,{description: description, nbrParticipants: nbrParticipants, date: date, CentreInt:CentreInt,lieu: lieu, adr_proposer: adr_proposer},httpOptions)
      .pipe(
        tap(data => console.log(data)),
        catchError(this.handleError('Addevent'))
      )
  }
  getPlace(adr: String,type: String) : Observable<any>{
    return this.http.post(this.urlDemande,{adr : adr,type: type},httpOptions)
      .pipe(
        tap(data => console.log(data)),
        catchError(this.handleError('getPlace'))
      )
  }
  directions(adr1: String,adr2: String){
    return this.http.post(this.urlDir,{adr1: adr1,adr2: adr2})
      .pipe(
        tap(data => console.log(data)),
    catchError(this.handleError('directions'))
  )}
  getEventCanParticipate() : Observable<any>{
    return this.http.get('/CanPartcicipate')
      .pipe(
        tap(data => console.log(data)),
        catchError(this.handleError('getevent'))
      )
  }
  getPlaceBeetwenAdr(adr1: String,adr2: String,type: String){
    return this.http.post('//getPlaceBeetwen',{adr1:adr1,adr2:adr2,type:type})
      .pipe(
        tap(data => console.log(data)),
        catchError(this.handleError('getPlace'))
      )
  }
  Participe(Id: String,lieu:String) : Observable<any>{
    return this.http.post('Participate',{Id:Id,lieu:lieu})
      .pipe(
        tap(data =>console.log(data)),
        catchError(this.handleError('paricipate'))
      )
  }
  private handleError<T> (operation = 'operation', result?: T) {
    return (erreur: any): Observable<T> => {
      // log to console instead
      // TODO: better job of transforming error for user consumption
      this.log(`${erreur.message}`);

      console.log(`${erreur}`);
      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
  private log(message: String) {
    this.MessageErr = message;
  }

}
