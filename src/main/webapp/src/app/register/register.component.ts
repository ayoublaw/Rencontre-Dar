import { Component, OnInit } from '@angular/core';
import {LoginService} from '../login.service';

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
    private loginSrevice: LoginService
  ) { }

  ngOnInit() {
  }
  register(email: String, password: String, nom: String, prenom: String, age: String, sex: String, centreInt: String[]){
    this.loginSrevice.register(email, password, nom, prenom, age, sex, centreInt);
  }

}
