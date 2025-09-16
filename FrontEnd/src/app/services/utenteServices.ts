import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UtenteServices {
  
  url='http://localhost:8080/rest/utente/'
  constructor(private http: HttpClient) { }

  signin(body:{}){
    return  this.http.post(this.url+'login' , body );
  }
  registrazione(body:{}){
    return this.http.post(this.url+'insert' , body );
  }
  findById(id : number){
    let params = new HttpParams().set('id', id);
    return  this.http.get(this.url+'findById' , {params} );
  }
  delete(id: number) {
    return  this.http.post(this.url+'delete' , {'id': id} );
  }
}
