import { Component, OnInit } from '@angular/core';
import { FotoBackend } from '../../services/foto-backend';
import { MatDialog } from '@angular/material/dialog';
import { FormDialog } from '../form-dialog/form-dialog';
import { Upload } from '../../services/upload';

@Component({
  selector: 'app-foto',
  standalone: false,
  templateUrl: './foto.html',
  styleUrl: './foto.css'
})
export class Foto implements OnInit{

  response: any;
  foto : any;
  msg:string = '';

  constructor(private fotoService:FotoBackend, private dialog: MatDialog, private uploadService: Upload){}

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
    // apro il dialog (modal) e gli passo la tipologia dell'oggetto (nel mio caso 'foto')
    const dialogRef = this.dialog.open(FormDialog, {
      width: '400px',
      data: { type: 'foto' }
    });

    // quando il dialog si chiude mi torna i dati
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        console.log("Payload ricevuto dal dialog: ", result);

        this.fotoService.insertFoto(result).subscribe((resp: any) => {
          if (resp.rc) {
            window.location.reload(); // se tutto va bene refresh pagina
          }
          else {
            // se l'insert di foto non va a buon fine elimino la foto che avevo caricato
            this.uploadService.deleteFile(result.immagine).subscribe((resp:any) => {
            console.log(resp.msg);
          })
          
          this.msg = resp.msg;
          console.log(this.msg);
          }
        });
      }
    });
}

}

