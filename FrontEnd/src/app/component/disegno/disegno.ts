import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { BackendDisegnoService } from '../../services/backend-disegno-service';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-disegno',
  standalone: false,
  templateUrl: './disegno.html',
  styleUrl: './disegno.css'
})
export class Disegno implements OnInit{
  //readonly dialog = inject(MatDialog);
  id:number = 0;
  DisegnoSelezionato:any;
  msg="";

  updateForm: FormGroup = new FormGroup({
    autore: new FormControl(),
    dataCreazione: new FormControl(),
    descrizione: new FormControl(),
    dimensione: new FormControl(),
    immagine: new FormControl(),
    isAI: new FormControl(),
    prezzo: new FormControl(),
    supporto: new FormControl(),
    tecnica: new FormControl(),
    titolo: new FormControl()
});
  constructor(private route:ActivatedRoute,
    private service:BackendDisegnoService,
    private routing:Router,
    private cdr: ChangeDetectorRef){}
  
  ngOnInit(): void {
    // controllo della variazione dei parametri di paramMap
    this.route.paramMap.subscribe((params:ParamMap) => {
      const idStr = params.get("id");
      if (idStr) {
        this.id =+ idStr; //equivalente a parseInt()
          this.service.findById(this.id)
            .subscribe((resp:any) => {
              if (resp.rc) {
                this.DisegnoSelezionato = resp.dati;
                console.log("Dati ricevuti e pronti per l'aggiornamento del form:", this.DisegnoSelezionato);
                this.updateForm.patchValue(this.DisegnoSelezionato.oggetto);
                this.updateForm.patchValue({
                    tecnica: this.DisegnoSelezionato.tecnica,
                    supporto: this.DisegnoSelezionato.supporto
                  });
                console.log("Dati caricati. Modifica il form e clicca 'Aggiorna'");
                this.cdr.detectChanges();
              } else {
                this.msg = "Nessun dato trovato per questo ID.";
                this.cdr.detectChanges();
              }
            /*
            this.updateForm = new FormGroup({
              autore: new FormControl(this.DisegnoSelezionato.oggetto.autore, Validators.required),
              dataCreazione: new FormControl(this.DisegnoSelezionato.oggetto.dataCreazione, Validators.required),
              descrizione: new FormControl(this.DisegnoSelezionato.oggetto.descrizione, Validators.required),
              dimensione: new FormControl(this.DisegnoSelezionato.oggetto.dimensione, Validators.required),
              immagine: new FormControl(this.DisegnoSelezionato.oggetto.immagine, Validators.required),
              isAI: new FormControl(this.DisegnoSelezionato.oggetto.isAI, Validators.required),
              prezzo: new FormControl(this.DisegnoSelezionato.oggetto.prezzo, Validators.required),
              supporto: new FormControl(this.DisegnoSelezionato.supporto, Validators.required),
              tecnica: new FormControl(this.DisegnoSelezionato.tecnica, Validators.required),
              titolo: new FormControl(this.DisegnoSelezionato.oggetto.titolo, Validators.required)
            });
            */
            });
      }
    })
  }
  /*
  autore: new FormControl(),
    dataCreazione: new FormControl(),
    descrizione: new FormControl(),
    dimensione: new FormControl(),
    immagine: new FormControl(),
    isAI: new FormControl(),
    prezzo: new FormControl(),
    supporto: new FormControl(),
    tecnica: new FormControl(),
    titolo: new FormControl()
  
  */
  onSubmit(){
    const updateBody:any = {id: this.id};
    if (this.updateForm.controls['autore'].touched) {
      updateBody.autore = this.updateForm.value.autore;
    } 
    if (this.updateForm.controls['dataCreazione'].touched) {
      updateBody.dataCreazione = this.updateForm.value.dataCreazione;
    }
    if (this.updateForm.controls['descrizione'].touched) {
      updateBody.descrizione = this.updateForm.value.descrizione;
    }
    if (this.updateForm.controls['dimensione'].touched) {
      updateBody.dimensione = this.updateForm.value.dimensione;
    }
    if (this.updateForm.controls['immagine'].touched) {
      updateBody.immagine = this.updateForm.value.immagine;
    }
    if (this.updateForm.controls['isAI'].touched) {
      updateBody.isAI = this.updateForm.value.isAI;
    } 
    if (this.updateForm.controls['supporto'].touched) {
      updateBody.supporto = this.updateForm.value.supporto;
    }
    if (this.updateForm.controls['tecnica'].touched) {
      updateBody.tecnica = this.updateForm.value.tecnica;
    }
    if (this.updateForm.controls['titolo'].touched) {
      updateBody.titolo = this.updateForm.value.titolo;
    }
    console.log(updateBody);

    
    this.service.updateDisegno(updateBody)
      .subscribe((resp:any) => {
        if (resp.rc){
          this.routing.navigate(["/disegni"])
            .then(() => {
              window.location.reload();
            });
        } else {
          this.msg = resp.msg;
        }
      });
  }
  /*
  onDelete() {
    
    console.log("onDelete");
    const enterAnimationDuration = '2000ms';
    const exitAnimationDuration = '2000ms';

    const dialogRef = this.dialog.open(DeleteContattoComponent, {
      width: '250px',
      enterAnimationDuration,
      exitAnimationDuration,
      data: {
        persona: this.personalContatto
      }, restoreFocus: false
    })

    dialogRef.afterClosed()
      .subscribe(resp => {
        if(resp=='si'){
          this.onDeleteAction();
        }
      })
  }

  onDeleteAction(){
    console.log("onDeleteAction");
    this.service.removePersona({
      id: this.id
    })
      .subscribe((resp:any) => {
        if (resp.rc){
          this.routing.navigate(["/contact"])
            .then(() => {
              window.location.reload();
            });
        } else {
          this.msg = resp.msg;
        }
      });
  }
  */
  onAnnul(){
    this.routing.navigate(["/disegni"])
      .then(() => {
        window.location.reload();
      });
  }
}

