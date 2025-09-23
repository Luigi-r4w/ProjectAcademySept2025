import { NgModule, provideBrowserGlobalErrorListeners, provideZonelessChangeDetection } from '@angular/core';
import { BrowserModule, provideClientHydration, withEventReplay } from '@angular/platform-browser';

import { CommonModule } from '@angular/common';
import {MatFormFieldModule} from '@angular/material/form-field'; 
import { FormsModule } from '@angular/forms'; 

import { provideHttpClient, withFetch } from '@angular/common/http';
import { MatCardModule } from '@angular/material/card';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { MatInputModule} from '@angular/material/input';
import {MatGridListModule} from '@angular/material/grid-list';
import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import { Home } from './component/home/home';
import { Sidebar } from './shared/sidebar/sidebar/sidebar';
import { Header } from './shared/header/header/header';
import { Foto } from './component/foto/foto';
import { Utente } from './component/utente/utente';
import { Login } from './component/login/login';
import {MatButtonModule} from '@angular/material/button';
import {MatDialogModule} from '@angular/material/dialog';
import { FormDialog } from './component/form-dialog/form-dialog';
import { Illustrazioni } from './component/illustrazioni/illustrazioni'
import { Disegni } from './component/disegni/disegni';
import { DeleteOggetto } from './dialog/delete-oggetto/delete-oggetto';


@NgModule({
  declarations: [
    App,
    Home,
    Sidebar,
    Header,
    Foto,
    FormDialog,
    Utente,
    Login,
    Disegni,
    Illustrazioni,
    DeleteOggetto
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatCardModule,
    MatButtonModule,
    MatDialogModule,
    FormsModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    CommonModule,
    MatInputModule,
    MatCheckboxModule,
    RouterModule,
    MatGridListModule
  ],
  providers: [
    provideHttpClient((withFetch())),
    provideBrowserGlobalErrorListeners(),
    provideZonelessChangeDetection(),
    provideClientHydration(withEventReplay())
  ],
  bootstrap: [App]
})
export class AppModule { }
