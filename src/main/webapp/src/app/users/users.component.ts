import { Component, OnInit } from '@angular/core';
import {LoginService} from "../login.service";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {
  AllUsers : any;
  MessageAcc: String;
  Affiche: number;
  constructor(
    public LoginService : LoginService
  ) { }

  ngOnInit() {
    this.GetAllUsers();
  }
  GetAllUsers(){
    return this.LoginService.GetAllUsers()
      .subscribe(data =>this.AllUsers = data)
  }
  SignalCompte(nom:String,prenom:String,Description){
    return this.LoginService.SignalCompte(nom,prenom,Description)
      .subscribe(data =>this.MessageAcc = data.message)
  }
  affiche(id:number){
    this.Affiche = id;
  }

}
