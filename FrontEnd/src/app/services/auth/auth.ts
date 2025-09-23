import { Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';


@Injectable({
  providedIn: 'root'
})
export class Auth {
  
  constructor(@Inject(PLATFORM_ID) private platformId: Object) {

    if (isPlatformBrowser(this.platformId)) {
      console.log('AUTH...')
      const isLoggedInValue = localStorage.getItem("isLoggedIn");
      const isAdminValue = localStorage.getItem("isAdmin");
      const idValue = localStorage.getItem('idUsr');
  
        if (isLoggedInValue !== null && isAdminValue !== null && idValue !== null) {
          console.log("Token exists");
        } else {
          localStorage.setItem("isLoggedIn", "0");
          localStorage.setItem("isAdmin", "0");
          localStorage.setItem("idUsr", "0");
        }
      }
  }

  isAutentificated() {
    if (isPlatformBrowser(this.platformId)) {
      if (localStorage.getItem("isLoggedIn") == '1'){
        console.log('è autenticato')
        return true;
      }
    }
    return false;
  }

  setId(id : number){
    if (isPlatformBrowser(this.platformId)) {
      localStorage.setItem('idUsr', id.toString());
    }
  }

  getId() {
    if (isPlatformBrowser(this.platformId)){
      console.log('id'+localStorage.getItem("idUsr"))
      return Number(localStorage.getItem("idUsr"));
    }
    return 0;
  }

  setAuthentificated() {
    if (isPlatformBrowser(this.platformId)) {
      localStorage.setItem("isLoggedIn", "1");
    }
  }

  setIsAdmin(){
    if (isPlatformBrowser(this.platformId)) {
      localStorage.setItem("isAdmin", "1");
    }
  }

  getIsAdmin(){
    if (isPlatformBrowser(this.platformId)) {
      if (localStorage.getItem("isAdmin") == '1'){
        console.log('è admin')
        return true;
      }
    }
    return false;
  }

  resetAll() {
    if (isPlatformBrowser(this.platformId)) {
      localStorage.setItem("isLoggedIn", "0")
      localStorage.setItem("isAdmin", "0")
      localStorage.setItem("idUsr", "0");
    }
  }
}
