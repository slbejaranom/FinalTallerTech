import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { ConveniosComponent } from './components/convenios/convenios.component';
import { LlamadasComponent } from './components/llamadas/llamadas.component';
import { ConfigArchivoComponent } from './components/config-archivo/config-archivo.component';

import { HttpClientModule } from "@angular/common/http";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'

import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule, MatPaginatorModule, MatRadioModule, MatSelectModule, MatSlideToggleModule, MatTableModule } from '@angular/material';
import { FormsModule } from '@angular/forms';
import { ConversionPipe } from './components/convenios/ConversionPipe';
import { CustomPipe } from './components/config-archivo/CustomPipe';
import { ParamPipe } from './components/config-archivo/ParamPipe';
import { CrearConvenioComponent } from './components/crear-convenio/crear-convenio.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ConveniosComponent,
    LlamadasComponent,
    ConfigArchivoComponent,
    ConversionPipe,
    CustomPipe,
    ParamPipe,
    CrearConvenioComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatIconModule,
    MatButtonModule,
    FormsModule,
    MatTableModule,
    MatPaginatorModule,
    MatSlideToggleModule,
    MatRadioModule,
    MatSelectModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
