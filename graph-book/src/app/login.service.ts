import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import {Student} from "./student"

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  
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
}
