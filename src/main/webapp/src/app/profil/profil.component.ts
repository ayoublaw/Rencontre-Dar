import { Component, OnInit } from '@angular/core';
import {EvenementService} from "../evenement.service";
import {AddressService} from "../address.service";

@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.css']
})
export class ProfilComponent implements OnInit {
  eventActif :any;
  eventParic :any;
  MessageAcc :any;
  PlaceDetails :any[] = [];
  currentUser : any;
  constructor(
    public eventService: EvenementService,
    public addressService: AddressService
  ) { }

  ngOnInit() {
    this.GetMyEventActif();
    this.GetMyParticiActif();
    this.CurrentUser();
  }
  GetMyEventActif(){
    return this.eventService.GetMyEventActif()
      .subscribe(data =>
      {this.eventActif = data;
        if(this.eventActif != null){
          for(let propo of this.eventActif){
            if(propo.Lieu != null){
            this.PlaceDeatil(propo.Lieu);}
          }
        }
      })
  }
  GetMyParticiActif(){
    return this.eventService.GetMyParticiActif()
      .subscribe(data =>
      {this.eventParic = data
        if(this.eventParic != null){
          for(let propo of this.eventParic){
            this.PlaceDeatil(propo.Lieu);
          }
        }
      })
  }
  DeleteEvent(evenementId: number){
    return this.eventService.DeleteEvenement(evenementId)
      .subscribe(data => {
        this.GetMyEventActif();
        this.MessageAcc = data.message;
      })
  }
  DeleteParticipation(evenementId: number){
    return this.eventService.DeleteParticipation(evenementId)
      .subscribe(data => {
        this.GetMyParticiActif();
        this.MessageAcc = data.message;
      })
  }
  PlaceDeatil(id:String){
    return this.eventService.PlaceDetails(id)
      .subscribe(data => this.PlaceDetails.push(data))
  }
  CurrentUser(){
    this.eventService.CurrentUser()
      .subscribe(data =>this.currentUser = data)
  }


}
