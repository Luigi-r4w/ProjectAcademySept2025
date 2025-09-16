import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BackendDisegnoService {
  url='http://localhost:8080/rest/disegno/';
  constructor(private http : HttpClient) { }

  listDisegni(){
    return this.http.get(this.url + 'listAll');
  }

  findById(id:number){
    console.log("FindById: " + id);
    let params = new HttpParams().set('id', id);
    return this.http.get(this.url + 'findById', {params});
  }

  updateDisegno(body:{}){
    return this.http.put(this.url + 'update', body);
  }
}
