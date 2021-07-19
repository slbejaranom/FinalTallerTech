import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { InicialComponent } from './components/inicial/inicial.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatGridList } from '@angular/material';

@NgModule({
  declarations: [
    AppComponent,
    InicialComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatGridList
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
