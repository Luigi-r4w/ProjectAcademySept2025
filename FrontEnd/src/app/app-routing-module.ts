import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Home } from './component/home/home';
import { Foto } from './component/foto/foto';

const routes: Routes = [
  {path:'', redirectTo: 'home', pathMatch: 'full'},
  {path:'home', component: Home, children: [
    {path:'', redirectTo: 'foto', pathMatch: 'full'},
    {path: 'foto', component: Foto}
  ]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
