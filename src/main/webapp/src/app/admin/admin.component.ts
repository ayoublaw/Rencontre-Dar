import { Component, OnInit } from '@angular/core';
import {LoginService} from "../login.service";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  listSignalCompte : any[];
  MessageConf : String;
  constructor(
    public LoginService : LoginService
  ) { }

  ngOnInit() {
    this.ListSignalCompte();
  }
  ListSignalCompte(){
    this.LoginService.ListSignalCompte()
      .subscribe(data => this.listSignalCompte =data)
  }
  ConfirmSignalCompte(Email:String){
    this.LoginService.ConfirmSignalCompte(Email)
      .subscribe(data =>
      {this.ListSignalCompte();
        this.MessageConf = data.message})

  }
}
