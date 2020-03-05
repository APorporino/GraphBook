import { Component, OnInit } from '@angular/core';
import { Student } from '../student';
import {LoginService} from "../login.service"
import { HttpClient } from '@angular/common/http';



@Component({
  selector: 'app-conncetions-list',
  templateUrl: './conncetions-list.component.html',
  styleUrls: ['./conncetions-list.component.scss']
})
export class ConncetionsListComponent implements OnInit {

  readonly pageUrl = 'https://graphbook-backend.herokuapp.com/connections';
  users: any; 
  currentUser: Student; // stores the Current User
  loginToken: boolean;

  constructor(private http:HttpClient,private data: LoginService) { }

  ngOnInit(): void {
    this.data.currentUser.subscribe(user=>this.currentUser=user)
    console.log(this.currentUser);
  }

}
