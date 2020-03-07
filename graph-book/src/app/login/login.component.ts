import { Component, OnInit,Input } from '@angular/core';
import { Observable } from 'rxjs';
import {HttpClient, HttpClientModule,HttpParams} from "@angular/common/http";
import {Student} from '../student'
import {CurrentUser} from "../currentUser"
import {LoginService} from "../login.service"
import {Router} from '@angular/router';



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
  @Input() signIn = {
    email:'',
    password:''
  }
  @Input() error='';
  @Input() message='';
  y= CurrentUser.constant2();

  public currentUser: Student;

  static constant2() { return "f"; }
  
  constructor(private http:HttpClient, private data: LoginService,private router: Router) { }

  ngOnInit(): void {
  }
  login(){
    let body = new HttpParams();
    body = body.set('email', this.signIn.email);
    body = body.set('password', this.signIn.password);
    this.http.get<Student>('https://graphbook-backend.herokuapp.com/login',{ params: body}).subscribe(data => {
      console.log(data);
      if (data!= null){
        this.currentUser=data;
        console.log(this.currentUser)
        this.data.changeMessage(this.currentUser);
        this.data.loggedIn(this.currentUser.studentId);
        this.router.navigate(["./profile"])

      }
  });}
  createUser(){
    let body = new HttpParams();
    body = body.set('firstName', this.signUpInfo.firstName);
    body = body.set('lastName', this.signUpInfo.lastName);
    body = body.set('studentId', this.signUpInfo.studentId);
    body = body.set('emailAddress', this.signUpInfo.emailAddress);
    body = body.set('password', this.signUpInfo.password);
    this.http.post('https://graphbook-backend.herokuapp.com/students/createStudent',body).subscribe(data => {
      console.log(data);
      this.message = "Your Account Has Been Created, Please Sign in"
      this.router.navigate(["./profile"])
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

