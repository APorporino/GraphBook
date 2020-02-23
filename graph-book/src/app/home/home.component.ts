import { Component, OnInit, Injectable } from '@angular/core';
import {HttpClient, HttpClientModule,HttpParams} from "@angular/common/http";
import {MatCardModule} from '@angular/material/card';
import {NgModule} from '@angular/core';
import { Observable } from 'rxjs';



@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})

@Injectable({providedIn:'root'})
export class HomeComponent implements OnInit {

  readonly pageUrl = 'http://graphbook-backend.herokuapp.com/students';
  users: any; 
  
    constructor(private http:HttpClient) { }
    
    getUsers(): Observable<Object>{
      console.log("get users() works!")
      return this.users = this.http.get(this.pageUrl)
      
    }

    getAllPosts(): Observable<Object>{
      return this.http.get('https://jsonplaceholder.typicode.com/posts')
    }
    
    ngOnInit() {
      this.getAllPosts().subscribe(data=>

        console.log(data))
    }

}
