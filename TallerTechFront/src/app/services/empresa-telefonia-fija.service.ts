import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { EmpresaTelefoniaFija } from '../models/empresaTelefoniaFija';

@Injectable({
  providedIn: 'root'
})
export class EmpresaTelefoniaFijaService {

  ruta = "http://localhost:3000/api/empresaTelefoniaFija";
  constructor(private httpClient : HttpClient) { }

  getCantidadConvenios(){
    return this.httpClient.get<number>(this.ruta+"/count");
  }

  getConvenioByNit(nit : string){
    return this.httpClient.get<EmpresaTelefoniaFija>(this.ruta+"/"+nit.toString());
  }

  getAllConvenios(){
    return this.httpClient.get<EmpresaTelefoniaFija[]>(this.ruta);
  }
}
