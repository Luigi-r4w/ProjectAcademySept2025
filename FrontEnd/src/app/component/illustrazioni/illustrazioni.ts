import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { BackendIllustrazioneService } from '../../services/backend-illustrazioni-service';
import { FormDialog } from '../form-dialog/form-dialog';
import { MatDialog } from '@angular/material/dialog';
import { Upload } from '../../services/upload';
import { UtenteServices } from '../../services/utenteServices';

@Component({
  selector: 'app-illustrazioni',
  standalone: false,
  templateUrl: './illustrazioni.html',
  styleUrl: './illustrazioni.css'
})
export class Illustrazioni implements OnInit{
  response:any;
  illustrazioni:any;
  msg:string = '';
  constructor(private service:BackendIllustrazioneService,
    private changeDetectorRef:ChangeDetectorRef,
    private dialog: MatDialog,
    private uploadService: Upload,
    private utenteService:UtenteServices
  ){}
  ngOnInit(): void {
    console.log("ngOnInit Illustrazioni");
    this.checkIllustrazioni();
  }
  checkIllustrazioni():void{
    this.service.listAll()
      .subscribe((resp:any) => {
        this.response = resp;
        console.log(resp);
        this.illustrazioni = this.response.list;

        this.changeDetectorRef.detectChanges();
      });
  }
  
  // to open insert form dialog
  openInsertDialog() {
    const dialogRef = this.dialog.open(FormDialog, {
      width: '400px',
      data: { type: 'illustrazione' }
    });

    // quando il dialog si chiude mi torna i dati (grazie Elia per il codice)
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        console.log("Payload ricevuto dal dialog: ", result);

        this.service.insert(result).subscribe((resp: any) => {
          if (resp.rc) {
            window.location.reload(); // se tutto va bene refresh pagina
            console.log(resp.msg)
          }
          else {
            // se l'insert di foto non va a buon fine elimino la foto che avevo caricato
            this.uploadService.deleteFile(result.immagine).subscribe((respCreate:any) => {
            console.log(respCreate.msg)
          })
          console.log(resp.msg)
          }
        });
      }
    });
  }

  openModifyDialog(id:number){
    console.log("Modify illustration id: " + id)
    var illustrazione:any
    this.service.findById(id).subscribe((resp:any)=>{
      if(resp.rc){
        illustrazione = resp.dati
      }else{
        console.log(resp.msg)
      }
      this.changeDetectorRef.detectChanges

      var dialogRef = this.dialog.open(FormDialog, {
        width: '400px',
        data: { type: 'illustrazione', oggetto : illustrazione}
      })

      dialogRef.afterClosed().subscribe((result:any)=>{
        if(result){
          this.modifyImage(result)
        }else{
          console.log("Nothing to update")
        }
      })
    })
  }

  modifyImage(result:any){
    console.log("Trying to modify the illustration")
      this.service.update(result).subscribe((resp:any) =>{
        if(resp.rc){
          console.log("Image updated")
          window.location.reload()
        }else{
          console.log("Failed update: deleting caricated image from our database")
          this.uploadService.deleteFile(result.immagine).subscribe((respDelete:any) => {
            console.log(respDelete.msg)
          })
        console.log(resp.msg)
        }
      })
  }

  openDeleteDialog(id:number){
    var illustrazione:any
    this.service.findById(id).subscribe((resp:any)=>{
       if(resp.rc){
        illustrazione = resp.dati
      }else{
        console.log(resp.msg)
      }
      this.changeDetectorRef.detectChanges

      if(confirm("Are you sure you want to delete the image '" + illustrazione.titolo +"'?" )){
        this.service.delete(illustrazione).subscribe((resp:any)=>{
          console.log("Image deleted")
          window.location.reload()
        })
      }else{
        console.log("Failed delete")
        console.log(resp.msg)
      }
    })
  }

  onBuy(){
    //this.utenteService.addItem(X, y);
  }
}
