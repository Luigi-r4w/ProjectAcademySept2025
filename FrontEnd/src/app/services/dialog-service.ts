// Crea un nuovo file: src/app/services/dialog.service.ts
import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Observable } from 'rxjs';
import { Upload } from './upload';
import { FormDialog } from '../component/form-dialog/form-dialog';
import { InfoDialog } from '../component/info-dialog/info-dialog';
import { TipoOggetto } from '../models/dialog-data';

@Injectable({ providedIn: 'root' })
export class DialogService {
  constructor(private dialog: MatDialog, private uploadService: Upload) {}

  openGenericDialog(type: TipoOggetto, insertMethod: (data: any) => Observable<any>, updateMethod: (data: any) => Observable<any>, oggetto?: any) {
    
    const dialogRef = this.dialog.open(FormDialog, {
      width: '400px',
      data: { type, oggetto }
    });

    dialogRef.afterClosed().subscribe((result: any) => {
      if (result) {
        const action = oggetto ? updateMethod(result) : insertMethod(result);

        action.subscribe((resp: any) => {
          if (resp.rc) {
            window.location.reload();
          } else {
            this.uploadService.deleteFile(result.immagine).subscribe();
          }
        });
      }
    });
  }

  openInfoDialog(data: any) {
    this.dialog.open(InfoDialog, {
      width: '70vw',
      maxWidth: '70vw',
      height: '80vh',
      enterAnimationDuration: '300ms',
      exitAnimationDuration: '300ms',
      data: data
    });
  }
}