import { Inject, Injectable, PLATFORM_ID } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class Auth {

  isLogged = true;
  
  constructor() {}

  isAutentificated() {
    return this.isLogged;
  }

  setAuthentificated() {
    this.isLogged = true;
  }

  resetAll() {
    this.isLogged = false;
  }
}
