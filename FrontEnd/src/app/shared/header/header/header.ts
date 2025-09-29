import { ChangeDetectorRef, Component, OnDestroy, OnInit } from '@angular/core';
import { Auth } from '../../../services/auth/auth';
import { UtenteServices } from '../../../services/utenteServices';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-header',
  standalone: false,
  templateUrl: './header.html',
  styleUrl: './header.css'
})
export class Header implements OnInit, OnDestroy{
  
  user='Profilo';
  private authSubscription: Subscription = new Subscription();
  isLoggedIn: boolean = false;

  constructor(private auth : Auth, private utente:UtenteServices, private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.authSubscription = this.auth.isLoggedIn$.subscribe(isAuthenticated => {
      this.isLoggedIn = isAuthenticated;
      if (isAuthenticated) {
        this.loadNome();
      } else {
        this.user = 'Profilo';
      }
    });
    if (this.auth.isAutentificated()) {
      this.loadNome();
    }
  }

  loadNome(){
    this.utente.findById(this.auth.getId() ).subscribe((resp: any) => {
      console.log(resp);
      if(resp.rc){
        this.user=resp.dati.nome;
        this.cdr.detectChanges();
      } else {
        this.user='Profilo';
      }
    })
  }

  ngOnDestroy(): void {
    if (this.authSubscription) {
      this.authSubscription.unsubscribe();
    }
  }

}
