import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Auth } from '../../services/auth/auth';
import { NgForm } from '@angular/forms';
import { Utente } from '../../services/utente';

@Component({
  selector: 'app-login',
  standalone: false,
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login {
  constructor(private utente : Utente, private router:Router, private auth:Auth){}

  ngOnInit(): void {
}

  isLogin = true;

  onSubmit(signin:NgForm) {
    console.log(signin.form.value.nome+" : "+signin.form.value.pasword)
    if(this.isLogin){
      this.utente.signin({
        nome: signin.form.value.nome,
        pwd: signin.form.value.pasword
      }).subscribe((resp: any) => {
        console.log(resp);
        if(resp.rc){
          console.log('utente logged .. role '+resp.msg)
          this.auth.setAuthentificated();
          this.router.navigate(['home'])
        }
      })
    } else{
      this.utente.registrazione({
        userName: signin.form.value.nome,
        pwd: signin.form.value.pwd,
        email: signin.form.value.email,
      }).subscribe((resp: any) => {
        console.log(resp);
        if(resp.rc){
          console.log('utente logged .. role '+resp.msg)
          this.auth.setAuthentificated();
          this.router.navigate(['home'])
        }
      })
    }
    
  }

  toggleForm(): void {
    this.isLogin = !this.isLogin;
  }
}
