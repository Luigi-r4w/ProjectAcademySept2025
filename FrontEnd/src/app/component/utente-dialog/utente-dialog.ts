import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-utente-dialog',
  standalone: false,
  templateUrl: './utente-dialog.html',
  styleUrl: './utente-dialog.css'
})
export class UtenteDialog {

  constructor(@Inject(MAT_DIALOG_DATA) public data: any) { }
  
}
