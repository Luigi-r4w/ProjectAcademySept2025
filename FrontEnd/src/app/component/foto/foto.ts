import { Component, OnInit } from '@angular/core';
import { FotoBackend } from '../../services/foto-backend';
import { MatDialog } from '@angular/material/dialog';
import { FormDialog } from '../form-dialog/form-dialog';

@Component({
  selector: 'app-foto',
  standalone: false,
  templateUrl: './foto.html',
  styleUrl: './foto.css'
})
export class Foto implements OnInit{

  response: any;
  foto : any;

  constructor(private fotoService:FotoBackend, private dialog: MatDialog){}

  ngOnInit(): void {
    console.log("ngOnInit");
    this.fotoService.listFoto()
      .subscribe(resp => {
        this.response = resp;
        this.foto = this.response.list;
        console.log(this.foto);
    })
  }

  // to open insert form dialog
  openDialog() {
    const dialogRef = this.dialog.open(FormDialog, {
      width: '400px'
    });

  }
}
