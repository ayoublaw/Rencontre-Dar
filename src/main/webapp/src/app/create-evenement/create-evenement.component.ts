import { Component, OnInit } from '@angular/core';
import {EvenementService} from "../evenement.service";

@Component({
  selector: 'app-create-evenement',
  templateUrl: './create-evenement.component.html',
  styleUrls: ['./create-evenement.component.css']
})
export class CreateEvenementComponent implements OnInit {
  AcceptMessage : String;
  centreIntTab: String[] = ['accounting', 'airport', 'amusement_park' , 'aquarium', 'art_gallery', 'atm'];
  ListPlace : any[];
  constructor(
   public evenService : EvenementService,
  ) { }

  ngOnInit() {
  }
  Addaevent(description: String,nbrParticipants: String,date: String,CentreInt: String,lieu: String,adr_proposer: String){
    this.evenService.Addevent(description,nbrParticipants,date,CentreInt,lieu,adr_proposer)
      .subscribe(data => this.AcceptMessage = data.message)
  }
  getPlace(adr: String,type: String){
    this.evenService.getPlace(adr,type)
      .subscribe(data => this.ListPlace = data)
  }


}
