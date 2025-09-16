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
        this.cdr.detectChanges();
      } else {
        this.msgEr=resp.msg;
        this.cdr.detectChanges();
      }
    })
  }

  deleteAccount() {
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
