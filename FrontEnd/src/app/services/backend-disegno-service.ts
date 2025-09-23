import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BackendDisegnoService {
  url = 'http://localhost:8080/rest/disegno/';

  constructor(private http : HttpClient) {}

  listDisegni(){
    return this.http.get(this.url + 'listAll');
  }
  
  insertDisegno(body:{}){
    return this.http.post(this.url + 'create', body)
  }
  findDisegnoByID(id:number){
    return this.http.get(this.url + 'findById?id=' + id)
  }
  updateDisegno(body:{}){
    console.log("update" + body);
    return this.http.put(this.url + 'update', body)
  }
  deleteDisegno(body:{}){
    console.log("delete: " + body);
    return this.http.post(this.url + 'delete', body)
  }
}
