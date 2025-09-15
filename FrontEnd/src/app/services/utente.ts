import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class Utente {
  url='http://localhost:8080/rest/utente/'
  constructor(private http: HttpClient) { }

  signin(body:{}){
    return  this.http.post(this.url+'signin' , body );
  }
  registrazione(body:{}){
    return this.http.post(this.url+'create' , body );
  }
}
