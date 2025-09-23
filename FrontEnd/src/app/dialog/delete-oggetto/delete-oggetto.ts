import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-delete-oggetto',
  standalone: false,
  templateUrl: './delete-oggetto.html',
  styleUrl: './delete-oggetto.css'
})
export class DeleteOggetto {
  oggettoDelete: any;

  constructor(
    @Inject(MAT_DIALOG_DATA) private data:any,
    private dialogRef: MatDialogRef<DeleteOggetto>){
      console.log("Sono sul costruttore: " + data.oggettoDelete.oggetto.titolo)
      if (data){
        this.oggettoDelete = data.oggettoDelete;
        
      }
  }

  optionSelected(opt:string){
    this.dialogRef.close(opt);
  }
}
