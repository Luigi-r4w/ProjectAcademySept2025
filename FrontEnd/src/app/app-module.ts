import { NgModule, provideBrowserGlobalErrorListeners, provideZonelessChangeDetection } from '@angular/core';
import { BrowserModule, provideClientHydration, withEventReplay } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import { Home } from './component/home/home';
import { Auth } from './services/auth';
import { Sidebar } from './shared/sidebar/sidebar/sidebar';
import { Header } from './shared/header/header/header';
import { Foto } from './component/foto/foto';
import { provideHttpClient, withFetch } from '@angular/common/http';
import {MatCardModule} from '@angular/material/card';
import {MatButtonModule} from '@angular/material/button';
import {MatDialogModule} from '@angular/material/dialog';
import { FormDialog } from './component/form-dialog/form-dialog';

@NgModule({
  declarations: [
    App,
    Home,
    Sidebar,
    Header,
    Foto,
    FormDialog
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatCardModule,
    MatButtonModule,
    MatDialogModule
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
