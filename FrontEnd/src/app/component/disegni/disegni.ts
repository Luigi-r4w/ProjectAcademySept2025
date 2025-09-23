import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { FormDialog } from '../form-dialog/form-dialog';
import { Upload } from '../../services/upload';
import { BackendDisegnoService } from '../../services/backend-disegno-service';
import { Auth } from '../../services/auth/auth';
import { InfoDialog } from '../info-dialog/info-dialog';
import { UtenteServices } from '../../services/utenteServices';

@Component({
  selector: 'app-disegni',
  standalone: false,
  templateUrl: './disegni.html',
  styleUrl: './disegni.css'
})
export class Disegni implements OnInit{

  response: any;
  disegni : any;
  msg:string = '';
  disegnoSingolo:any;
  private dialogRef: any;


  constructor(private disegnoService:BackendDisegnoService,
    private dialog: MatDialog,
    private uploadService: Upload,
    private cdr: ChangeDetectorRef,
    private utenteService: UtenteServices,
    private auth: Auth){}

  ngOnInit(): void {
    console.log("ngOnInit");
    this.disegnoService.listDisegni()
      .subscribe(resp => {
        this.response = resp;
        this.disegni = this.response.list;
        console.log(this.disegni);
        this.cdr.detectChanges();
    })
  }

  // to open insert form dialog
  openDialog() {
    // apro il dialog (modal) e gli passo la tipologia dell'oggetto (nel mio caso 'foto')
    this.dialogRef = this.dialog.open(FormDialog, {
      width: '400px',
      data: { type: 'disegno' }
    });

    // quando il dialog si chiude mi torna i dati
    this.dialogRef.afterClosed().subscribe((result:any) => {
      if (result) {
        console.log("Payload ricevuto dal dialog: ", result);

        this.disegnoService.insertDisegno(result).subscribe((resp: any) => {
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
    this.disegnoService.findDisegnoByID(id).subscribe((resp:any) => {
      if(resp.rc){
        this.disegnoSingolo = resp.dati
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
        data: { type: 'disegno', oggetto : this.disegnoSingolo }
      });

      // quando il dialog si chiude mi torna i dati
      this.dialogRef.afterClosed().subscribe((result:any) => {
        if (result) {
          console.log("Modify: Payload ricevuto dal dialog: ", result);

          this.disegnoService.updateDisegno(result).subscribe((resp: any) => {
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

  openInfoDialog(disegno:any){
        this.dialog.open(InfoDialog, {
          width: '70vw',
          maxWidth: '70vw',
          height: '80vh',
          enterAnimationDuration: '300ms',
          exitAnimationDuration: '300ms',
          data: disegno 
        })
    }
  
    addToCart(id:number){
      let utenteID = this.auth.getId(); 
      this.utenteService.addItem(utenteID, id).subscribe();
      console.log("aggiunto al carrello");
    }
}

