import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BackendDisegnoService {
  url = 'http://localhost:8080/rest/disegno/';

  constructor(private http : HttpClient) {}

  listDisegni(){
    return this.http.get(this.url + 'list');
  }
  
  insertDisegno(body:{}){
    return this.http.post(this.url + 'insert', body)
  }
  findDisegnoByID(id:number){
    return this.http.get(this.url + 'findById?id=' + id)
  }
  updateDisegno(body:{}){
    console.log("update");
    return this.http.put(this.url + 'update', body)
  }
}
