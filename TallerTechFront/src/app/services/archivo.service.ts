import { Injectable } from '@angular/core';
import { EmpresaTelefoniaFija } from '../models/empresaTelefoniaFija';

@Injectable({
  providedIn: 'root'
})
export class ArchivoService {

  constructor() { }

  ConvertToCSV(objArray, headerList) {
    let array = typeof objArray != 'object' ? JSON.parse(objArray) : objArray;
    let str = '';
    let row = 'S.No,';
    for (let index in headerList) {
     row += headerList[index] + ',';
    }
    row = row.slice(0, -1);
    str += row + '\r\n';
    for (let i = 0; i < array.length; i++) {
     let line = (i+1)+'';
     for (let index in headerList) {
      let head = headerList[index];
      line += ',' + array[i][head];
     }
     str += line + '\r\n';
    }
    return str;
   }

  ConvertToCSV1(objArray, headerList) {
    let array = typeof objArray != 'object' ? JSON.parse(objArray) : objArray;
    let str = '';
    let row = 'S.No,';
    for (let index in headerList) {
     row += headerList[index] + ',';
    }
    row = row.slice(0, -1);
    str += row + '\r\n';
    for (let i = 0; i < array.length; i++) {
     let line = (i+1)+'';
     for (let j = 0; j < headerList.length; j++) {
      let head = headerList[j];
      line += ',' + array[i][j];
     }
     str += line + '\r\n';
    }
    return str;
   }

   downloadConveniosFile(data, filename='data') {
    let csvData = this.ConvertToCSV(data, ['nit','razon_social', 'fecha_creacion', 'esta_activo', 'periodicidad_archivo', 'primer_param_archivo', 'segundo_param_archivo', 'tercer_param_archivo','cuarto_param_archivo','quinto_param_archivo']);
    let blob = new Blob(['\ufeff' + csvData], { type: 'text/csv;charset=utf-8;' });
    let dwldLink = document.createElement("a");
    let url = URL.createObjectURL(blob);
    let isSafariBrowser = navigator.userAgent.indexOf('Safari') != -1 && navigator.userAgent.indexOf('Chrome') == -1;
    if (isSafariBrowser) {  //if Safari open in new window to save file with random filename.
        dwldLink.setAttribute("target", "_blank");
    }
    dwldLink.setAttribute("href", url);
    dwldLink.setAttribute("download", filename + ".csv");
    dwldLink.style.visibility = "hidden";
    document.body.appendChild(dwldLink);
    dwldLink.click();
    document.body.removeChild(dwldLink);
  }
  
  downloadReporteFile(data, filename='data', empresa : EmpresaTelefoniaFija){
    console.log(data);
    let headers = [];
    switch(empresa.primer_param_archivo){
      case 0:
        headers.push("Fecha");
        break;
      case 1:
        headers.push("Telefono de Origen");
        break;
      case 2:
        headers.push("Telefono de Destino");
        break;
      case 3:
        headers.push("Valor");
        break;
      case 4:
        headers.push("Duración");
        break;
    }
    switch(empresa.segundo_param_archivo){
      case 0:
        headers.push("Fecha");
        break;
      case 1:
        headers.push("Telefono de Origen");
        break;
      case 2:
        headers.push("Telefono de Destino");
        break;
      case 3:
        headers.push("Valor");
        break;
      case 4:
        headers.push("Duración");
        break;
    }
    switch(empresa.tercer_param_archivo){
      case 0:
        headers.push("Fecha");
        break;
      case 1:
        headers.push("Telefono de Origen");
        break;
      case 2:
        headers.push("Telefono de Destino");
        break;
      case 3:
        headers.push("Valor");
        break;
      case 4:
        headers.push("Duración");
        break;
    }
    switch(empresa.cuarto_param_archivo){
      case 0:
        headers.push("Fecha");
        break;
      case 1:
        headers.push("Telefono de Origen");
        break;
      case 2:
        headers.push("Telefono de Destino");
        break;
      case 3:
        headers.push("Valor");
        break;
      case 4:
        headers.push("Duración");
        break;
    }
    switch(empresa.quinto_param_archivo){
      case 0:
        headers.push("Fecha");
        break;
      case 1:
        headers.push("Telefono de Origen");
        break;
      case 2:
        headers.push("Telefono de Destino");
        break;
      case 3:
        headers.push("Valor");
        break;
      case 4:
        headers.push("Duración");
        break;
    }
    console.log(headers);
    let csvData = this.ConvertToCSV1(data, headers);
    let blob = new Blob(['\ufeff' + csvData], { type: 'text/csv;charset=utf-8;' });
    let dwldLink = document.createElement("a");
    let url = URL.createObjectURL(blob);
    let isSafariBrowser = navigator.userAgent.indexOf('Safari') != -1 && navigator.userAgent.indexOf('Chrome') == -1;
    if (isSafariBrowser) {  //if Safari open in new window to save file with random filename.
        dwldLink.setAttribute("target", "_blank");
    }
    dwldLink.setAttribute("href", url);
    dwldLink.setAttribute("download", filename + ".csv");
    dwldLink.style.visibility = "hidden";
    document.body.appendChild(dwldLink);
    dwldLink.click();
    document.body.removeChild(dwldLink);
  }
}
