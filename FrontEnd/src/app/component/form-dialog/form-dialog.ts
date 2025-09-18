import { Component, inject, Inject, OnInit } from '@angular/core';
import { Upload } from '../../services/upload';
import { FormControl, FormGroup } from '@angular/forms';
import { Foto } from '../foto/foto';
import { FotoBackend } from '../../services/foto-backend';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';

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

  constructor(@Inject(MAT_DIALOG_DATA) public data: any,
    private uploadService: Upload, 
    private fotoService: FotoBackend){
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
    if (!this.selectedFile) {   // sicurezza extra
      console.log("Nessun file selezionato");
      return;
    }
  
  // chiamo il controller upload del backend che mi torna il nome del file
  this.uploadService.upload(this.selectedFile).subscribe((resp: any) => {
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
        categoria: 'foto',
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

}
