import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ErrMessageService {
  message: String = null ;
  constructor() {}
  SetMessage(m: String) {
    this.message = m;
  }
}
