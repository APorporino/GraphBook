import { Student } from './student';

export class CurrentUser {
    studentId: Number;
    emailAddress: string;
    studentFirstName:string;
    studentLastName:string;

constructor(){
    this.studentId = -1;
    this.emailAddress = "";
    this.studentFirstName = "";
    this.studentLastName = "";
  }
  
  static dd = "eg";
  static constant2() { 
      this.dd="fe";
     }
     static constant3() { 
        return this.dd;
       }
}
