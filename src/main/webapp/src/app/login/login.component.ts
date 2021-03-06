import { Component, OnInit } from '@angular/core';
import {LoginService} from '../login.service';
import {Router} from "@angular/router";
import {ErrMessageService} from "../err-message.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(
    private loginService: LoginService,
    private MessageErr: ErrMessageService,
    private router: Router
  ) { }

  ngOnInit() {
  }
  login(email: String, password: String) {
    this.loginService.login(email, password )
      .subscribe(
        data => {
          console.log('Erreur :' +this.MessageErr.message);
          if(this.MessageErr.message == 'Email or Username Invalid' || this.MessageErr.message == 'Compte Supprimer'){
            this.router.navigate(['/login'])
          }
          else {
            this.router.navigate(['/dashboard'])
          }
        });
  }
}
