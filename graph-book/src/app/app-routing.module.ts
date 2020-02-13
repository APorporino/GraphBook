import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import {HomeComponent} from "./home/home.component";
import {AboutComponent} from "./about/about.component"
import { LoginComponent } from './login/login.component';
import { ProfileComponent } from './profile/profile.component';
import { ConnectionComponent } from './connection/connection.component';


const routes: Routes = [
  {path: '', redirectTo: 'login', pathMatch: 'full'},
  {path: 'about', component: AboutComponent},
  {path: 'login', component: LoginComponent},
  {path: 'profile', component: ProfileComponent},
  {path: 'home', component: HomeComponent},
  {path: 'connect', component: ConnectionComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
