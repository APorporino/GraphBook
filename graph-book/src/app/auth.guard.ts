import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import {LoginService} from "./login.service"

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private data: LoginService, private router: Router){}
  loginToken: boolean = false;
  
  canActivate(){
    this.data.currentToken.subscribe(token=>this.loginToken=token)
    //if(this.loginToken){
    if(true){
      console.log(this.loginToken)
      return true
    }else{
      this.router.navigate(["./login"])
      console.log("you can't navigate")
      return false
    }
  }
  
}
