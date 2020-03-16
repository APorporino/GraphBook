import { Component, OnInit, Injectable, Input} from '@angular/core';
import {HttpClient, HttpClientModule,HttpParams} from "@angular/common/http";
import { Observable } from 'rxjs';
import { Student } from '../student';
import {LoginService} from "../login.service";


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
@Injectable({providedIn:'root'})
export class ProfileComponent implements OnInit {

  readonly pageUrl = 'https://graphbook-backend.herokuapp.com/students';
  users: any;
  connect: true;
  currentUser: Student;
  loginToken: boolean;

  constructor(private data: LoginService,private http:HttpClient) { }

getUsers(): Observable<Object>{
  console.log("get users() works!")
  let body = new HttpParams();
  body = body.set('email', '1234');
  return this.users = this.http.get(this.pageUrl)
}

getAllPosts(): Observable<Object>{
  return this.http.get('https://jsonplaceholder.typicode.com/posts')
}

ngOnInit() {
  this.data.currentUser.subscribe(user=>this.currentUser=user)

  console.log(this.currentUser);

}
changeEmail(email){
    var studentID = this.currentUser.studentId;
    console.log(studentID)


}

}
