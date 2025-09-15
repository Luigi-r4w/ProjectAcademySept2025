import { NgModule, provideBrowserGlobalErrorListeners, provideZonelessChangeDetection } from '@angular/core';
import { BrowserModule, provideClientHydration, withEventReplay } from '@angular/platform-browser';

import {MatFormFieldModule} from '@angular/material/form-field'; 
import { FormsModule } from '@angular/forms'; 
import { provideHttpClient, withFetch } from '@angular/common/http';




import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import { Home } from './component/home/home';
import { Sidebar } from './shared/sidebar/sidebar/sidebar';
import { Header } from './shared/header/header/header';
import { Utente } from './component/utente/utente';
import { Login } from './component/login/login';

@NgModule({
  declarations: [
    App,
    Home,
    Sidebar,
    Header,
    Utente,
    Login
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatFormFieldModule,
    FormsModule,
  ],
  providers: [
    provideHttpClient(withFetch()),
    provideBrowserGlobalErrorListeners(),
    provideZonelessChangeDetection(),
    provideClientHydration(withEventReplay())
  ],
  bootstrap: [App]
})
export class AppModule { }
