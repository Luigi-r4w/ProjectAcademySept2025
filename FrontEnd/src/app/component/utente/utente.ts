import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Auth } from '../../services/auth/auth';
import { UtenteServices } from '../../services/utenteServices';
import { Router } from '@angular/router';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { UtenteDialog } from '../utente-dialog/utente-dialog';
import { TyDialog } from '../ty-dialog/ty-dialog';

@Component({
  selector: 'app-utente',
  standalone: false,
  templateUrl: './utente.html',
  styleUrl: './utente.css'
})

export class Utente implements OnInit {

  constructor(private auth:Auth, private utente:UtenteServices, private router:Router, private cdr: ChangeDetectorRef,   private dialog: MatDialog  ){}

  nome!:string;
  email!:string;
  carrello: any;
  msgEr: any;
  isLoading: boolean = true;
  isAdmin= false;

  ngOnInit(): void {
    console.log("findbyId di : " + this.auth.getId() );
    this.utente.findById(this.auth.getId() ).subscribe((resp: any) => {
      console.log(resp);
      if(resp.rc){
        this.nome=resp.dati.nome;
        this.email=resp.dati.email;
        this.carrello=resp.dati.carrello;
        this.isLoading = false;
        this.msgEr=null;
        this.isAdmin=this.auth.getIsAdmin();
        this.cdr.detectChanges();
      } else {
        this.msgEr=resp.msg;
        this.cdr.detectChanges();
      }
    })
  }

  deleteAccount() {
    const confirmed = window.confirm('Sei sicuro di voler eliminare il tuo account?');
    if(confirmed){
      console.log("cancello l'account id : " + this.auth.getId() )
      this.utente.delete(this.auth.getId() ).subscribe((resp: any) => {
        console.log(resp);
        if(resp.rc){
          console.log("cancellazione effettuata");
          this.auth.resetAll();
          console.log(this.auth.isAutentificated());
          this.router.navigate(['login']);
        } else{
          console.log(resp.msg)
        }
      })
    }
  }

  get totalAmount() {
    return this.carrello.reduce((acc: number, item: any) => acc + item.prezzo, 0);
  }  

  confirmPurchase() {
    const dialogRef = this.dialog.open(UtenteDialog, {
      width: '400px', 
      data: {
        carrello: this.carrello,
        totalAmount: this.totalAmount,
        onConfirm: () => this.finalizePurchase(dialogRef),
        onCancel: () => dialogRef.close()
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('Dialogo chiuso', result);
    });
  }

  finalizePurchase(dialogRef: MatDialogRef<UtenteDialog, any>) {
    this.utente.svuotaCarrello(this.auth.getId()).subscribe((resp: any) => {
      if (resp.rc) {
        console.log("Acquisto effettuato");
        dialogRef.close();
        this.showThankYouMessage();
      } else {
        console.log(resp.msg);
        this.msgEr=resp.msg;
      }
    });
  }

  showThankYouMessage() {
    const dialogRef = this.dialog.open(TyDialog, {
      width: '300px',
    });

    dialogRef.afterClosed().subscribe(() => {
      this.router.navigate(['/home']);
    });
  }

  removeItem(idItem: number) {
    const confirmed = window.confirm('Sei sicuro di voler rimuovere questo prodotto dal carrello?');
    if(confirmed){
      this.utente.rmItem(this.auth.getId(), idItem ).subscribe((resp: any) => {
        console.log(resp);
        if(resp.rc){
          console.log("cancellazione effettuata");
          this.ngOnInit();
        } else{
          console.log(resp.msg)
        }
      })
    }
  }
  
}
