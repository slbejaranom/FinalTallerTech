import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { EmpresaTelefoniaFija } from '../models/empresaTelefoniaFija';
import { Llamada } from '../models/llamada';

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
    return this.httpClient.get<EmpresaTelefoniaFija>(this.ruta+"/"+nit);
  }

  getAllConvenios(){
    return this.httpClient.get<EmpresaTelefoniaFija[]>(this.ruta);
  }

  inactivarConvenio(convenio : EmpresaTelefoniaFija){
    return this.httpClient.put<EmpresaTelefoniaFija>(this.ruta+"/"+convenio.nit,convenio);
  }

  generarReporteDeLlamadas(nit : string){
    return this.httpClient.get<Llamada[]>(this.ruta+"/generarArchivo/"+nit);
  }

  modificarConvenio(convenio : EmpresaTelefoniaFija){
    return this.httpClient.put<EmpresaTelefoniaFija>(this.ruta+"/"+convenio.nit, convenio);
  }

  guardarConvenio(convenio : EmpresaTelefoniaFija){
    return this.httpClient.post<EmpresaTelefoniaFija>(this.ruta,convenio);
  }
}