import { ChangeDetectorRef, Component } from '@angular/core';
import { Router } from '@angular/router';
import { Auth } from '../../services/auth/auth';
import { NgForm } from '@angular/forms';
import { UtenteServices } from '../../services/utenteServices';

@Component({
  selector: 'app-login',
  standalone: false,
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login {
  constructor(private utente : UtenteServices, private router:Router, private auth:Auth, private cdr: ChangeDetectorRef){}

  ngOnInit(): void {
    this.auth.resetAll();
}

  isLogin = true;
  msgEr: any;


  onSubmit(signin:NgForm) {
    console.log(signin.form.value.nome+" : "+signin.form.value.pasword)
    if(this.isLogin){
      this.utente.signin({
        email: signin.form.value.email,
        password: signin.form.value.pwd
      }).subscribe((resp: any) => {
        console.log(resp);
        if(resp.rc){
          console.log('utente logged .. role '+resp.msg)
          this.auth.setAuthentificated();
          console.log(resp.dati.id);
          this.auth.setId(resp.dati.id);
          if(resp.dati.role=='ADMIN'){
            console.log("è un ADMIN")
            this.auth.setIsAdmin();
          } else{
            console.log("non è un ADMIN")
          }
          this.router.navigate(['home'])
        } else {
          this.msgEr=resp.msg;
          this.cdr.detectChanges();
        }
      })
    } else{
      this.utente.registrazione({
        nome: signin.form.value.nome,
        password: signin.form.value.pwd,
        email: signin.form.value.email,
        role: 'USER'
      }).subscribe((resp: any) => {
        console.log(resp);
        if(resp.rc){
          console.log('utente logged .. role '+resp.msg)
          this.auth.setAuthentificated();
          console.log(resp.msg);
          this.auth.setId(resp.msg);
          this.router.navigate(['home'])
        }else {
          this.msgEr=resp.msg;
          this.cdr.detectChanges();
        }
      })
    }
    
  }

  toggleForm(): void {
    this.isLogin = !this.isLogin;
  }
}
