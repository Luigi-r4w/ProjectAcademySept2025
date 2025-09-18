import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Auth } from '../../services/auth/auth';
import { UtenteServices } from '../../services/utenteServices';
import { Router } from '@angular/router';

@Component({
  selector: 'app-utente',
  standalone: false,
  templateUrl: './utente.html',
  styleUrl: './utente.css'
})

export class Utente implements OnInit {

  constructor(private auth:Auth, private utente:UtenteServices, private router:Router, private cdr: ChangeDetectorRef){}

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

  confirmPurchase() {
    const confirmed = window.confirm('Sei sicuro di voler acquistare questi prodotti?');
    if(confirmed){
      this.utente.svuotaCarrello(this.auth.getId()).subscribe((resp: any) => {
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
