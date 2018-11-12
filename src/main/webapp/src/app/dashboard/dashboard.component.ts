import { Component, OnInit } from '@angular/core';
import {DashboardService} from "../dashboard.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  Session : any;
  constructor(private dashboardService: DashboardService,
              private router: Router) { }

  ngOnInit() {
    this.getSession();
  }
  getSession(): void {
    this.dashboardService.getSession()
      .subscribe(data => {this.Session = data.session; console.log("test" + this.Session)})
  }

}
