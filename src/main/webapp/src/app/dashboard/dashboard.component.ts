import { Component, OnInit } from '@angular/core';
import {DashboardService} from "../dashboard.service";
import {Router} from "@angular/router";
import {EvenementService} from "../evenement.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  Session : any;
  constructor(private dashboardService: DashboardService,
              private router: Router,
              public eventService: EvenementService) { }

  ngOnInit() {
  }
  getSession(): void {
    this.dashboardService.getSession()
      .subscribe(data => {
        if(data.session == null){
          this.router.navigate(['login']);
        }
      })
  }
}
