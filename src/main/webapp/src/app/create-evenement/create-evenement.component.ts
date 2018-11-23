import { Component, OnInit } from '@angular/core';
import {EvenementService} from "../evenement.service";
import {Router} from "@angular/router";
import {AddressService} from "../address.service";
import {FormControl} from "@angular/forms";

@Component({
  selector: 'app-create-evenement',
  templateUrl: './create-evenement.component.html',
  styleUrls: ['./create-evenement.component.css']
})
export class CreateEvenementComponent implements OnInit {
  AcceptMessage : String;
  ListPlace : any[];
  Directions : any;
  centreIntTab: String[] = ['amusement_park', 'art_gallery', 'bar' , 'bowling_alley', 'art_gallery', 'gym','library','museum','night_club','park','restaurant','stadium','zoo'];
  myModelProperty: String;
  RadioModel: String;
  myControl ;
  constructor(
   public evenService : EvenementService,
   public router: Router,
   public addressService : AddressService,
) { }

  ngOnInit() {
    this.evenService.clean();
    this.myControl = new FormControl();
  }
  Addaevent(description: String,nbrParticipants: String,date: String,CentreInt: String,lieu: String,adr_proposer: String){
    if(lieu == undefined){
      lieu = null;
    }
    this.evenService.Addevent(description,nbrParticipants,date,CentreInt,lieu,adr_proposer)
      .subscribe(data => {
        this.AcceptMessage = data.message,
          this.router.navigate(['/'])
      })
  }
  getPlace(adr: String,type: String){
    this.evenService.getPlace(adr,type)
      .subscribe(data => this.ListPlace = data)
  }
  directions(adr1: String,adr2: String,place_id: String){
    this.evenService.directions(adr1,adr2,place_id)
      .subscribe(data => this.Directions = data)
  }


}
