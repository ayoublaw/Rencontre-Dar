import { Component, OnInit } from '@angular/core';
import {EvenementService} from "../evenement.service";

@Component({
  selector: 'app-evenement',
  templateUrl: './evenement.component.html',
  styleUrls: ['./evenement.component.css']
})
export class EvenementComponent implements OnInit {
  ListEvent: any;
  Directions: any;
  placesBeetwen: any;
  MessageAcc; String;
  PlaceDetails: any[] = [];
  constructor(
    public eventService: EvenementService
  ) { }

  ngOnInit() {
    this.getEventCanParticipate();
    this.eventService.clean();
  }
  getEventCanParticipate(){
    this.eventService.getEventCanParticipate()
      .subscribe(data => {
        this.ListEvent = null;
        this.ListEvent = data;
        if(this.ListEvent != null){
          for(let event of this.ListEvent){
            if(event.Lieu != null){
              this.PlaceDeatil(event.Lieu);
            }
          }
        }
      });
  }
  directions(adr1: String,adr2: String){
    this.Directions = null;
    this.eventService.directions(adr1,adr2)
      .subscribe(data => this.Directions = data)
  }
  getPlacesBeetwen(adr1: String,adr2: String,type: String){
    this.eventService.getPlaceBeetwenAdr(adr1,adr2,type)
      .subscribe(data => this.placesBeetwen = data);;
  }
  Participate(Id:String,lieu:String){
    this.eventService.Participe(Id,lieu)
      .subscribe(data => {
      this.getEventCanParticipate();
      this.MessageAcc = data.message;
      })
  }
  PlaceDeatil(id:String){
    return this.eventService.PlaceDetails(id)
      .subscribe(data => this.PlaceDetails.push(data))
  }
}
