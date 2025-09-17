import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FotoBackend {
  url = 'http://localhost:8080/rest/foto/';

  constructor(private http : HttpClient) {}

  listFoto(){
    return this.http.get(this.url + 'list');
  }
  
  insertFoto(body:{}){
    return this.http.post(this.url + 'insert', body)
  }
}


