import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BackendIllustrazioneService {
  url='http://localhost:8080/rest/illustrazione/'
  constructor(private http : HttpClient) { }

  listAll(){
    return this.http.get(this.url + 'list')
  }

  findById(id:number){
    console.log("FindById: " + id)
    let params = new HttpParams().set('id', id)
    return this.http.get(this.url + 'getById', {params})
  }

  update(body:{}){
    return this.http.put(this.url + 'update', body)
  }
  insert(body:{}){
    return this.http.post(this.url + 'insert', body)
  }
  delete(body:{}){
    return this.http.post(this.url + 'delete',body)
  }
}