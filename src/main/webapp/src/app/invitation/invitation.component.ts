import { Component, OnInit } from '@angular/core';
import {EvenementService} from "../evenement.service";

@Component({
  selector: 'app-invitation',
  templateUrl: './invitation.component.html',
  styleUrls: ['./invitation.component.css']
})
export class InvitationComponent implements OnInit {
  Propositions : any[];
  Responses : any;
  Directions : any;
  PlaceDeatils : any[] = [];
  MessageAcc : any;
  constructor(
    public eventService : EvenementService
  ) { }

  ngOnInit() {
  this.Proposition();
  this.ResponsesforOurPropositions();
  }

  Proposition(){
    return this.eventService.Propositions()
      .subscribe(data => {
        this.Propositions = data;
        this.PlaceDeatils = [];
        if(this.Propositions != null){
          for(let propo of this.Propositions){
            this.PlaceDeatil(propo.Lieu);
          }
        }
      });
  }
  ResponsesforOurPropositions() {
    return this.eventService.ReponsesforOurPropositions()
      .subscribe(data => this.Responses = data)
  }
  PlaceDeatil(id:String){
    return this.eventService.PlaceDetails(id)
      .subscribe(data => this.PlaceDeatils.push(data))
  }
  directions(adr1: String,adr2: String,place_id: String){
    this.Directions = null;
    this.eventService.directions(adr1,adr2,place_id)
      .subscribe(data => this.Directions = data)
  }
  AccepteOrRefuse(evenementID: number, b: Boolean){
    this.eventService.AccepteOrRefuse(evenementID,b)
      .subscribe(data =>  {
        this.MessageAcc = data.message;
        this.Proposition();
      })
  }
  DeleteParticipation(evenementId : number){
    this.eventService.DeleteParticipation(evenementId)
      .subscribe(data =>{
        this.MessageAcc = data.message;
        this.ResponsesforOurPropositions();
      })
  }
}
