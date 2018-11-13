import { Component, OnInit } from '@angular/core';
import {EvenementService} from "../evenement.service";

@Component({
  selector: 'app-evenement',
  templateUrl: './evenement.component.html',
  styleUrls: ['./evenement.component.css']
})
export class EvenementComponent implements OnInit {
  ListEvent: any;
  constructor(
    public eventService: EvenementService
  ) { }

  ngOnInit() {
    this.getEventCanParticipate();
  }
  getEventCanParticipate(){
    this.eventService.getEventCanParticipate()
      .subscribe(data => this.ListEvent = data);
  }

}
