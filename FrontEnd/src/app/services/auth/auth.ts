import { Inject, Injectable, PLATFORM_ID } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class Auth {

  isLogged = false;
  id!: number;
  
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

  resetAll() {
    this.isLogged = false;
    this.id=0;
  }
}
