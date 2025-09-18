import { Inject, Injectable, PLATFORM_ID } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class Auth {

  isLogged = false;
  id!: number;
  isAdmin = false;
  
  constructor() {}

  isAutentificated() {
    return this.isLogged;
  }

  setId(iD : number){
    this.id=iD;
  }

  getId() {
    return this.id;
  }

  setAuthentificated() {
    this.isLogged = true;
  }

  setIsAdmin(){
    this.isAdmin = true;
  }

  getIsAdmin(){
    return this.isAdmin;
  }

  resetAll() {
    this.isLogged = false;
    this.id=0;
    this.isAdmin = false;
  }
}
