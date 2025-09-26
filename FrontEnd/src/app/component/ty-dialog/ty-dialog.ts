import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-ty-dialog',
  standalone: false,
  templateUrl: './ty-dialog.html',
  styleUrl: './ty-dialog.css'
})
export class TyDialog {

  constructor(private dialogRef: MatDialogRef<TyDialog>) {}

  close(): void {
    this.dialogRef.close();
  }
}
