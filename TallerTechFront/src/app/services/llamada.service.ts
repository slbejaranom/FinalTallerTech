import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Llamada } from '../models/llamada';

@Injectable({
  providedIn: 'root'
})
export class LlamadaService {
  ruta = "http://localhost:3000/api/llamada";

  constructor(private httpClient : HttpClient) { }

  getLlamadasPorConvenio(nit : string){
    return this.httpClient.get<Llamada[]>(this.ruta+"/convenio/"+nit);
  }

  getLlamadas(){
    return this.httpClient.get<Llamada[]>(this.ruta);
  }
}
