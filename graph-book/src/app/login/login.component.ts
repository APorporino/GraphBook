import { Component, OnInit,Input } from '@angular/core';
import { Observable } from 'rxjs';
import {HttpClient, HttpClientModule,HttpParams} from "@angular/common/http";
import {Student} from '../student'
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  
  @Input() signUpInfo = {
    studentId:'',
    firstName:'',
    lastName:'',
    emailAddress:'',
    password:'',
  }
  @Input() error=''
  @Input() message=''
  
  constructor(private http:HttpClient) { }

  ngOnInit(): void {
  }
  createUser(){
    let body = new HttpParams();
    body = body.set('firstName', this.signUpInfo.firstName);
    body = body.set('lastName', this.signUpInfo.lastName);
    body = body.set('studentId', this.signUpInfo.studentId);
    body = body.set('emailAddress', this.signUpInfo.emailAddress);
    body = body.set('password', this.signUpInfo.password);
    this.http.post('http://graphbook-backend.herokuapp.com/students/createStudent',body).subscribe(data => {
      console.log(data);
      this.message = "Your Account Has Been Created, Please Sign in"
    },
    error => {
      this.error = error.error.message;
      }
    );
   
  }
  // createUser(){
  //   this.http.post('http://graphbook-backend.herokuapp.com/students/createStudent',this.signUpInfo)
  //   console.log(this.signUpInfo)
  // }

}
