import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FotoBackend } from '../../services/foto-backend';
import { MatDialog } from '@angular/material/dialog';
import { FormDialog } from '../form-dialog/form-dialog';
import { Upload } from '../../services/upload';
import { InfoDialog } from '../info-dialog/info-dialog';
import { UtenteServices } from '../../services/utenteServices';
import { Auth } from '../../services/auth/auth';
import { ConfirmDialog } from '../confirm-dialog/confirm-dialog';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-foto',
  standalone: false,
  templateUrl: './foto.html',
  styleUrl: './foto.css'
})
export class Foto implements OnInit{

  constructor(private fotoService:FotoBackend, 
    private dialog: MatDialog, 
    private uploadService: Upload, 
    private cdr: ChangeDetectorRef,
    private utenteService: UtenteServices,
    private auth: Auth,
    private snackBar: MatSnackBar){}

  response: any;
  foto : any;
  msg:string = '';
  fotoSingola:any;
  private dialogRef: any;
  isAdmin: any;
  

  ngOnInit(): void {
    console.log("ngOnInit");
    this.isAdmin = this.auth.getIsAdmin();
    this.fotoService.listFoto()
      .subscribe(resp => {
        this.response = resp;
        this.foto = this.response.list;
        console.log(this.foto);
        this.cdr.detectChanges();
    })
  }

  // to open insert form dialog
  openDialog() {
    // apro il dialog (modal) e gli passo la tipologia dell'oggetto (nel mio caso 'foto')
    this.dialogRef = this.dialog.open(FormDialog, {
      width: '400px',
      data: { type: 'foto' }
    });

    // quando il dialog si chiude mi torna i dati
    this.dialogRef.afterClosed().subscribe((result:any) => {
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

  openModifyDialog(id:number){
    console.log("id: ", id);
    // recupero i dati della foto che ho cliccato
    this.fotoService.getFotoByID(id).subscribe((resp:any) => {
      if(resp.rc){
        this.fotoSingola = resp.dati
        console.log("dati: " + resp.dati.oggetto.titolo)
        this.cdr.detectChanges();
      }
      else{
        console.log(resp.msg);
        this.cdr.detectChanges();
      }

      // apro il dialog (modal) e gli passo la tipologia dell'oggetto (nel mio caso 'foto')
      this.dialogRef = this.dialog.open(FormDialog, {
        width: '400px',
        data: { type: 'foto', oggetto : this.fotoSingola }
      });

      // quando il dialog si chiude mi torna i dati
      this.dialogRef.afterClosed().subscribe((result:any) => {
        if (result) {
          console.log("Modify: Payload ricevuto dal dialog: ", result);

          this.fotoService.modifyFoto(result).subscribe((resp: any) => {
            console.log("rc: ", resp.rc);
            console.log("autore: ", resp.autore);
            if (resp.rc) {
              console.log("immagine nuova ", result.immagine);
              window.location.reload(); // se tutto va bene refresh pagina
            }
            else {
              // se l'update di foto non va a buon fine elimino la foto che avevo caricato
              this.uploadService.deleteFile(result.immagine).subscribe((resp:any) => {
              console.log(resp.msg);
            })

            this.msg = resp.msg;
            console.log(this.msg);
            }
          });
        }
      });
    })
  }
  openInfoDialog(foto:any){
      this.dialog.open(InfoDialog, {
        width: '70vw',
        maxWidth: '70vw',
        height: '80vh',
        enterAnimationDuration: '300ms',
        exitAnimationDuration: '300ms',
        data: foto 
      })
  }

  addToCart(id:number){
    let utenteID = this.auth.getId(); 
    if (this.auth.isAutentificated()){
      this.utenteService.addItem(utenteID, id).subscribe();
      this.snackBar.open('Articolo aggiunto!', 'Chiudi', {
        duration: 2000,
        horizontalPosition: 'right',
        verticalPosition: 'top',
        panelClass: ['custom-snackbar']
      });
    }
    else {
      this.dialog.open(ConfirmDialog, {
          width: '300px',
          enterAnimationDuration: '500ms',
          exitAnimationDuration: '500ms'
        });
    }
  }
}