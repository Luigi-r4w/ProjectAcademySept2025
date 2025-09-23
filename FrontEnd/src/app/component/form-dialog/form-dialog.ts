import { ChangeDetectorRef, Component, inject, Inject, OnInit } from '@angular/core';
import { Upload } from '../../services/upload';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { FotoBackend } from '../../services/foto-backend';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { BackendDisegnoService } from '../../services/backend-disegno-service';
import { DeleteOggetto } from '../../dialog/delete-oggetto/delete-oggetto';
import { Router } from '@angular/router';
import { BackendIllustrazioneService } from '../../services/backend-illustrazioni-service';

@Component({
  selector: 'app-form-dialog',
  standalone: false,
  templateUrl: './form-dialog.html',
  styleUrl: './form-dialog.css'
})
export class FormDialog implements OnInit{
  uploadedFileName: string='';
  selectedFile?: File;
  uploadForm: any;
  readonly dialogRef = inject(MatDialogRef<FormDialog>);
  readonly dialogDelete = inject(MatDialog);
  oggettoRemove: any;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any,
    private uploadService: Upload, 
    private fotoService: FotoBackend,
    private disegnoService: BackendDisegnoService,
    private illustrazioneService: BackendIllustrazioneService,
    private cdr: ChangeDetectorRef,
    private routing:Router,){
    console.log("Tipo ricevuto:", data.type);
  }
  // quando il componente viene inizializzato in base al tipo costruisce il gli input del form
  ngOnInit(): void {
    console.log("ngOnInit");

    this.uploadForm = new FormGroup({
      titolo: new FormControl(),
      descrizione: new FormControl(),
      autore: new FormControl(),
      dataCreazione: new FormControl(),
      dimensione: new FormControl(),
      prezzo: new FormControl(),
      isAI: new FormControl(false),
      categoria: new FormControl(),
      immagine: new FormControl()
    });

    if (this.data.type === 'foto') {
      this.uploadForm.addControl('device', new FormControl());
      this.uploadForm.addControl('widthResolution', new FormControl());
      this.uploadForm.addControl('heightResolution', new FormControl());
    }

    if (this.data.type === 'disegno') {
      this.uploadForm.addControl('supporto', new FormControl());
      this.uploadForm.addControl('tecnica', new FormControl());
    }

    if (this.data.type === 'illustrazione') {
      this.uploadForm.addControl('urlIllustrazione', new FormControl());
      this.uploadForm.addControl('stile', new FormControl());
      this.uploadForm.addControl('dataIllustrazione', new FormControl());

    }

    // se il dialog riceve i dati di una foto gia esistente riempe il form
    if (this.data.oggetto) {
      this.riempiCampiForm();
    }
    
  }

  onFileSelected(event: Event) {
    console.log("sono qui");
    // event.target è l'elemento che ha generato l'evento
    const input = event.target as HTMLInputElement;

    if (input.files && input.files.length > 0) {
      this.selectedFile = input.files[0];
    }
  }

  onSubmit(){
    
    if (this.data.oggetto)
      return this.modifyForm();
    else
      return this.createForm();

  }

  modifyForm(){
    const updateBody: any = {id: this.data.oggetto.id}

    // funzione insterna
    const completaFormEInvia = () => {
      if (this.uploadForm.controls['titolo'].touched)
      updateBody.titolo = this.uploadForm.value.titolo;

      if (this.uploadForm.controls['descrizione'].touched)
        updateBody.descrizione = this.uploadForm.value.descrizione;

      if (this.uploadForm.controls['autore'].touched)
        updateBody.autore = this.uploadForm.value.autore;

      if (this.uploadForm.controls['dataCreazione'].touched)
        updateBody.dataCreazione = this.uploadForm.value.dataCreazione;

      if (this.uploadForm.controls['dimensione'].touched)
        updateBody.dimensione = this.uploadForm.value.dimensione;

      if (this.uploadForm.controls['prezzo'].touched)
        updateBody.prezzo = this.uploadForm.value.prezzo;

      if (this.uploadForm.controls['isAI'].touched)
        updateBody.isAI = this.uploadForm.value.isAI;

      if (this.uploadForm.controls['device']?.touched)
        updateBody.device = this.uploadForm.value.device;

      if (this.uploadForm.controls['widthResolution']?.touched)
        updateBody.widthResolution = this.uploadForm.value.widthResolution;

      if (this.uploadForm.controls['heightResolution']?.touched)
        updateBody.heightResolution = this.uploadForm.value.heightResolution;

      if (this.uploadForm.controls['supporto']?.touched)
        updateBody.supporto = this.uploadForm.value.supporto;

      if (this.uploadForm.controls['tecnica']?.touched)
        updateBody.tecnica = this.uploadForm.value.tecnica;

      this.dialogRef.close(updateBody);
    }

    if(this.selectedFile){
      console.log("immagine vecchia: ", this.uploadForm.value.immagine);
      this.uploadService.deleteFile(this.uploadForm.value.immagine).subscribe((resp:any) => {
        console.log(resp.msg);
        this.uploadService.upload(this.selectedFile!).subscribe((resp: any) => {
          console.log('Risposta backend: ', resp);
      
          // assegno il nome del file
          this.uploadedFileName = resp;

          updateBody.immagine = this.uploadedFileName;
          completaFormEInvia();
        })
      })
    }
    else{
      completaFormEInvia();
    }

  }

  createForm() {
    if (!this.selectedFile) {   // sicurezza extra
      console.log("Nessun file selezionato");
      return;
    }
    // chiamo il controller upload del backend che mi torna il nome del file
    this.uploadService.upload(this.selectedFile!).subscribe((resp: any) => {
      console.log('Risposta backend: ', resp);
      
      // assegno il nome del file
      this.uploadedFileName = resp;

      // riempo i campi di oggetto
      const campiInComune = {
        titolo: this.uploadForm.value.titolo,
        descrizione: this.uploadForm.value.descrizione,
        autore: this.uploadForm.value.autore,
        dataCreazione: this.uploadForm.value.dataCreazione,
        dimensione: this.uploadForm.value.dimensione,
        prezzo: this.uploadForm.value.prezzo,
        isAI: this.uploadForm.value.isAI,
        categoria: this.data.type,
        immagine: this.uploadedFileName
      }

      // faccio una copia e la inserisco in payload
      let payload: any = { ...campiInComune };

      if (this.data.type === 'foto') {
      payload = {
        ...campiInComune,
        device: this.uploadForm.value.device,
        widthResolution: this.uploadForm.value.widthResolution,
        heightResolution: this.uploadForm.value.heightResolution
        };
      }
      if (this.data.type === 'disegno') {
      payload = {
        ...campiInComune,
        supporto: this.uploadForm.value.supporto,
        tecnica: this.uploadForm.value.tecnica
        };
      }
      if (this.data.type === 'illustrazione') {
      payload = {
        ...campiInComune,
        urlIllustrazione: this.uploadForm.value.urlIllustrazione,
        stile: this.uploadForm.value.stile,
        dataIllustrazione:this.uploadForm.value.dataIllustrazione
        };
      }
      // chiudo il dialog e ritorno l'oggetto compreso di tutti i suoi dati (nel mio caso a foto.ts)
      this.dialogRef.close(payload);
    });
  }

  // mi riempe i campi del form se voglio modificare
  riempiCampiForm() {
    const oggettoSingolo = this.data.oggetto
    // creo il form con i campi comuni, già precompilati
    this.uploadForm = new FormGroup({
      titolo: new FormControl(oggettoSingolo.oggetto.titolo,  Validators.required),
      descrizione: new FormControl(oggettoSingolo.oggetto.descrizione, Validators.required),
      autore: new FormControl(oggettoSingolo.oggetto.autore, Validators.required),
      dataCreazione: new FormControl(oggettoSingolo.oggetto.dataCreazione, Validators.required),
      dimensione: new FormControl(oggettoSingolo.oggetto.dimensione, Validators.required),
      prezzo: new FormControl(oggettoSingolo.oggetto.prezzo, [Validators.required, Validators.min(1)]),
      isAI: new FormControl(oggettoSingolo.oggetto.isAI),
      categoria: new FormControl(oggettoSingolo.oggetto.categoria),
      immagine: new FormControl(oggettoSingolo.oggetto.immagine)
    });

    // se la categoria è foto → aggiungo i campi extra e li precompilo
    if (oggettoSingolo.oggetto.categoria === 'foto') {
      this.uploadForm.addControl('device', new FormControl(oggettoSingolo.device, Validators.required));
      this.uploadForm.addControl('widthResolution', new FormControl(oggettoSingolo.widthResolution, [Validators.required, Validators.min(1)]));
      this.uploadForm.addControl('heightResolution', new FormControl(oggettoSingolo.heightResolution, [Validators.required, Validators.min(1)]));
    }
    else if (oggettoSingolo.oggetto.categoria === 'disegno') {
      this.uploadForm.addControl('supporto', new FormControl(oggettoSingolo.supporto, Validators.required));
      this.uploadForm.addControl('tecnica', new FormControl(oggettoSingolo.tecnica, Validators.required));
    }
  }
  
  
  onDelete(tipo:any, id:number) {
    console.log("onDelete");
    const enterAnimationDuration = '500ms';
    const exitAnimationDuration = '500ms';

    if (tipo === 'foto') {
      this.fotoService.getFotoByID(id).subscribe((resp:any) => {
        if(resp.rc){
          this.oggettoRemove = resp.dati
          this.cdr.detectChanges();
        }
        else{
          console.log(resp.msg);
          this.cdr.detectChanges();
        }
        
        const dialogRef = this.dialogDelete.open(DeleteOggetto, {
          width: '250px',
          enterAnimationDuration,
          exitAnimationDuration,
          data: {
            oggettoDelete: this.oggettoRemove
            
          }, restoreFocus: false
        })

        dialogRef.afterClosed()
          .subscribe((resp:any) => {
            if(resp=='si'){
              this.onDeleteAction();
            }
          })
      })
    } else if (tipo === 'disegno'){
      this.disegnoService.findDisegnoByID(id).subscribe((resp:any) => {
        if(resp.rc){
          this.oggettoRemove = resp.dati
          this.cdr.detectChanges();
        }
        else{
          console.log(resp.msg);
          this.cdr.detectChanges();
        }

        const dialogRef = this.dialogDelete.open(DeleteOggetto, {
          width: '250px',
          enterAnimationDuration,
          exitAnimationDuration,
          data: {
            oggettoDelete: this.oggettoRemove
            
          }, restoreFocus: false
        })

        dialogRef.afterClosed()
          .subscribe((resp:any) => {
            if(resp=='si'){
              this.onDeleteAction();
            }
          })
      })
    } else if (tipo === 'illustrazione'){
      this.illustrazioneService.findById(id).subscribe((resp:any) => {
        if(resp.rc){
          this.oggettoRemove = resp.dati
          this.cdr.detectChanges();
        }
        else{
          console.log(resp.msg);
          this.cdr.detectChanges();
        }

        const dialogRef = this.dialogDelete.open(DeleteOggetto, {
          width: '250px',
          enterAnimationDuration,
          exitAnimationDuration,
          data: {
            oggettoDelete: this.oggettoRemove
            
          }, restoreFocus: false
        })

        dialogRef.afterClosed()
          .subscribe((resp:any) => {
            if(resp=='si'){
              this.onDeleteAction();
            }
          })
      })
    }
  }

  onDeleteAction(){
    console.log("onDeleteAction");
    if (this.data.type === 'foto') {
      console.log("Sono in deleteAction: " + this.oggettoRemove.oggetto.titolo)
      this.fotoService.deleteFoto(this.oggettoRemove)
        .subscribe((resp:any) => {
          if (resp.rc){
            this.uploadService.deleteFile(this.oggettoRemove.oggetto.immagine)
              .subscribe();
            this.routing.navigate(["/foto"])
              .then(() => {
                window.location.reload();
              });
          }
        });
    } else if (this.data.type === 'disegno') {
      this.disegnoService.deleteDisegno(this.oggettoRemove)
        .subscribe((resp:any) => {
          if (resp.rc){
            console.log("Rimozione oggetto dalla cartella: " + this.oggettoRemove.oggetto.immagine)
            this.uploadService.deleteFile(this.oggettoRemove.oggetto.immagine)
              .subscribe();
            this.routing.navigate(["/disegni"])
              .then(() => {
                window.location.reload();
              });
          }
        });
    } else if (this.data.type === 'illustrazione') {
      this.illustrazioneService.delete(this.oggettoRemove)
        .subscribe((resp:any) => {
          if (resp.rc){
            this.uploadService.deleteFile(this.oggettoRemove.oggetto.immagine)
              .subscribe();
            this.routing.navigate(["/illustrazioni"])
              .then(() => {
                window.location.reload();
              });
          }
        });
    }
  }
  
}
