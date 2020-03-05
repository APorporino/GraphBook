import { Component, OnInit } from '@angular/core';
import { Student } from '../student';


@Component({
  selector: 'app-conncetions-list',
  templateUrl: './conncetions-list.component.html',
  styleUrls: ['./conncetions-list.component.scss']
})
export class ConncetionsListComponent implements OnInit {

  readonly pageUrl = 'https://graphbook-backend.herokuapp.com/students';
  users: any; 
  currentUser: Student;
  loginToken: boolean;

  constructor() { }

  ngOnInit(): void {
  }

}
