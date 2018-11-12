import { Component, OnInit } from '@angular/core';
import {ErrMessageService} from '../err-message.service';

@Component({
  selector: 'app-err-message',
  templateUrl: './err-message.component.html',
  styleUrls: ['./err-message.component.css']
})
export class ErrMessageComponent implements OnInit {

  constructor(public errMessage: ErrMessageService) { }

  ngOnInit() {
  }

}
