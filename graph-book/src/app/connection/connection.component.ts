import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Input } from '@angular/core';
import { HttpParams, HttpClient } from '@angular/common/http';
import { LoginService } from '../login.service';
import { Student } from '../student';

@Component({
  selector: 'app-connection',
  templateUrl: './connection.component.html',
  styleUrls: ['./connection.component.scss']
})
export class ConnectionComponent implements OnInit {
 
  @Input() user;
  @Input() connect;
  @Input() level;
  @Input() currentID;
  currentUser: Student;
  edge;

  constructor(private data: LoginService,private http:HttpClient) { }

  ngOnInit(): void {
    
    this.data.currentUser.subscribe(user=>this.currentUser=user)
    // this.currentID = this.data.
  }
  onClickMe(){
    let body = new HttpParams();
    body = body.set('followerId', <any> this.currentUser.studentId);
    body = body.set('followeeId', this.user.studentId);
    body = body.set('weight', this.level);

    console.log(body)
    this.http.post('https://graphbook-backend.herokuapp.com/edges/createEdge',body).subscribe(data => {
      console.log(data);
    this.edge=data
    
   })
  }

}
