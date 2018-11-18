import { Component, OnInit } from '@angular/core';
import {LoginService} from '../login.service';
import {Router} from "@angular/router";
import {ErrMessageService} from "../err-message.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  public myModelProperty: Array<any>;
  public myModeSex: String;
  public centreIntTab: String[] = ['accounting', 'airport', 'amusement_park' , 'aquarium', 'art_gallery', 'atm'];

  constructor(
    private loginSrevice: LoginService,
    private MessageErr: ErrMessageService,
    private router: Router
  ) { }

  ngOnInit() {
  }
  register(email: String, password: String, nom: String, prenom: String, age: String, sex: String, centreInt: String[]){
    this.loginSrevice.register(email, password, nom, prenom, age, sex, centreInt)
      .subscribe(
        data => {
          console.log('Erreur :' +this.MessageErr.message);
          if(this.MessageErr.message == 'Email Already used' || this.MessageErr.message === undefined){
            this.router.navigate(['/login'])
          }
          else {
            this.router.navigate(['/dashboard'])
          }
        });
  }

}
