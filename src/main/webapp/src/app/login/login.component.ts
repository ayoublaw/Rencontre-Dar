import { Component, OnInit } from '@angular/core';
import {LoginService} from '../login.service';
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(
    private loginService: LoginService,
    private router: Router
  ) { }

  ngOnInit() {
  }
  login(email: String, password: String) {
    this.loginService.login(email, password )
      .subscribe(data => {
          this.router.navigate(['/dashboard']);
        },
        error => {
         this.router.navigate(['/login']);
        });
  }
}
