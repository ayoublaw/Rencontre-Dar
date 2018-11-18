import { Component, OnInit } from '@angular/core';
import {LoginService} from "../login.service";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  ListSignalCompte : any[];
  MessageConf : String;
  constructor(
    public LoginService : LoginService
  ) { }

  ngOnInit() {
  }
  ListSignalCompte(){
    this.LoginService.ListSignalCompte()
      .subscribe(data => this.ListSignalCompte =data)
  }
  ConfirmSignalCompte(Email:String){
    this.LoginService.ConfirmSignalCompte(Email)
      .subscribe(data =>this.MessageConf = data.message)

  }
}
