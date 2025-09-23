import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-info-dialog',
  standalone: false,
  templateUrl: './info-dialog.html',
  styleUrl: './info-dialog.css'
})
export class InfoDialog {
  constructor(@Inject(MAT_DIALOG_DATA) public foto: any,
  private dialogRef: MatDialogRef<InfoDialog>){}

  onClose(){
    this.dialogRef.close();
  }
}
