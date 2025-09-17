import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Utente } from './component/utente/utente';
import { Home } from './component/home/home';
import { Login } from './component/login/login';
import { authGuard } from './services/auth/auth-guard';
import { Foto } from './component/foto/foto';

const routes: Routes = [
  {path: 'utente' , component : Utente, canActivate:[authGuard]},
  {path: 'home' , component: Home, canActivate:[authGuard]},
  {path: 'login' , component: Login},
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  {path: 'foto' , component: Foto},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
