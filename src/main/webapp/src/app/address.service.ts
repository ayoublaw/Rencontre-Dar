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
export class AddressService {
  public messageJuste ;
  public messageErr  ;
  public address;
  private url = '/addAdress';
  private urlget = '/ListAddress';
  constructor(
    private http: HttpClient
  ) { }

  AddAdress(nom: String,numero: String,rue: String,ville: String,codepostal: String) : Observable<any>{
    return this.http.post(this.url,{ nom: nom, numero: numero, rue: rue, ville: ville, codepostal: codepostal},httpOptions)
      .pipe(
        tap(data => console.log(data))
      )
  }
  clean(){
    this.messageErr = null;
    this.messageJuste = null;
  }
  getAddres() : Observable<any>{
    return this.http.get(this.urlget)
      .pipe(
        tap(data => this.address = data)
      )
  }
}
