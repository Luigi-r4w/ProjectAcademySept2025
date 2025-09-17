import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class Upload {
  private url = 'http://localhost:8080/rest/files/';

  constructor(private http: HttpClient) {}

  upload(file: File) {
    const formData = new FormData();
    formData.append('file', file); // "file" deve essere lo stesso nome del @RequestParam
    return this.http.post<string>(this.url + 'upload', formData, { responseType: 'text' as 'json' });
  }

  deleteFile(fileName:string){
    console.log("cerco di eliminare")
    console.log("filename: " + fileName)
    const urlCompleto = this.url + 'delete?filename=' + fileName;
    console.log("urlCompleto: " + urlCompleto);
    return this.http.post(this.url + 'delete?filename=' + fileName, {});
  }
}
