import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Input } from '@angular/core';

@Component({
  selector: 'app-connection',
  templateUrl: './connection.component.html',
  styleUrls: ['./connection.component.scss']
})
export class ConnectionComponent implements OnInit {
 
  @Input() user;
  constructor() { }

  ngOnInit(): void {
  }
  onClickMe(){
    console.log(this.user.emailAddress) // TODO Connection function.
  }

}
