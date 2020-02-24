import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import {Student} from "./student"

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  
  private token = new BehaviorSubject<boolean>(false);
  currentToken = this.token.asObservable();

  private messageSource = new BehaviorSubject<Student>({studentId:-1,
    firstName:"",
    lastName:"",
    emailAddress:"",
    password:""});
  currentUser = this.messageSource.asObservable();

  constructor() { }

  changeMessage( user : Student){
    this.messageSource.next(user);
  }

  loggedIn(id=-1){
    if (id!=-1){
      this.token.next(true)
    }else{
      this.token.next(false);
    }
  }
}
