import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ConfigArchivoComponent } from './components/config-archivo/config-archivo.component';
import { ConveniosComponent } from './components/convenios/convenios.component';
import { CrearConvenioComponent } from './components/crear-convenio/crear-convenio.component';
import { HomeComponent } from './components/home/home.component';
import { LlamadasComponent } from './components/llamadas/llamadas.component';

const routes: Routes = [
  { path: "" , component: HomeComponent },
  { path: "Convenios" , component: ConveniosComponent },
  { path: "Llamadas" , component: LlamadasComponent },
  { path: "ConfigArchivo" , component: ConfigArchivoComponent },
  { path: "CrearConvenio" , component: CrearConvenioComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
