import { Component, OnInit } from '@angular/core';
import {AddressService} from "../address.service";

@Component({
  selector: 'app-create-address',
  templateUrl: './create-address.component.html',
  styleUrls: ['./create-address.component.css']
})
export class CreateAddressComponent implements OnInit {
  ListAddress: any[];
  messageEmpty: String;
  constructor(
    public addressService: AddressService
  ) {}

  ngOnInit() {
    this.addressService.clean();
    this.getAddress();
  }
  addAddress(nom: String,numero: String,rue: String,ville: String,codepostal: String){
    this.addressService.AddAdress(nom,numero,rue,ville,codepostal)
      .subscribe(
        data => this.addressService.messageJuste = data.message,
        erreur => this.addressService.messageErr = erreur.Err
      );
  }
  getAddress(){
    this.addressService.getAddres()
      .subscribe(
        data => this.ListAddress = data,
        erreur => this.messageEmpty = erreur.Err
      )
  }


}
