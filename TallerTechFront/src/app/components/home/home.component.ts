import { Component, OnInit } from '@angular/core';
import { connectableObservableDescriptor } from 'rxjs/internal/observable/ConnectableObservable';
import { EmpresaTelefoniaFija } from 'src/app/models/empresaTelefoniaFija';
import { Status } from 'src/app/models/status';
import { ArchivoService } from 'src/app/services/archivo.service';
import { EmpresaTelefoniaFijaService } from 'src/app/services/empresa-telefonia-fija.service';
import { StatusService } from 'src/app/services/status.service';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  status : Status;
  diffTime : number;
  empresas : Array<EmpresaTelefoniaFija> = new Array();

  constructor(private archivoService : ArchivoService,private statusService : StatusService, private empresaTelefoniaService : EmpresaTelefoniaFijaService) {
    this.status = new Status();
  }

  ngOnInit() {
    this.statusService.getStatus().subscribe( value => {
      this.status = value;
   });
   this.empresaTelefoniaService.getAllConvenios().subscribe((val)=>{
      val.forEach(x => {
        if((Math.abs(new Date().getDate() - x.fecha_creacion.getDate()))/x.periodicidad_archivo % 1 == 0){
          this.empresaTelefoniaService.generarReporteDeLlamadas(x.nit);
          this.generarReporte(x);
        }
      });
   });
  }

  generarReporte(convenio : EmpresaTelefoniaFija){
    let reporteFinal = [];
    this.empresaTelefoniaService.generarReporteDeLlamadas(convenio.nit).subscribe( val => {
      val.forEach( x => {
        let aReportar = {};
        switch(convenio.primer_param_archivo){
          case 0:
            aReportar[0] = x.fecha;
            break;
          case 1:
            aReportar[0] = x.cliente.telefono;
            break;
          case 2:
            aReportar[0] = x.telefonoDestino;
            break;
          case 3:
            aReportar[0] = x.duracion*convenio.costo_segundo;
            break;
          case 4:
            aReportar[0] = x.duracion;
            break;
        }
        switch(convenio.segundo_param_archivo){
          case 0:
            aReportar[1] = x.fecha;
            break;
          case 1:
            aReportar[1] = x.cliente.telefono;
            break;
          case 2:
            aReportar[1] = x.telefonoDestino;
            break;
          case 3:
            aReportar[1] = x.duracion*convenio.costo_segundo;
            break;
          case 4:
            aReportar[1] = x.duracion;
            break;
        }
        switch(convenio.tercer_param_archivo){
          case 0:
            aReportar[2] = x.fecha;
            break;
          case 1:
            aReportar[2] = x.cliente.telefono;
            break;
          case 2:
            aReportar[2] = x.telefonoDestino;
            break;
          case 3:
            aReportar[2] = x.duracion*convenio.costo_segundo;
            break;
          case 4:
            aReportar[2] = x.duracion;
            break;
        }
        switch(convenio.cuarto_param_archivo){
          case 0:
            aReportar[3] = x.fecha;
            break;
          case 1:
            aReportar[3] = x.cliente.telefono;
            break;
          case 2:
            aReportar[3] = x.telefonoDestino;
            break;
          case 3:
            aReportar[3] = x.duracion*convenio.costo_segundo;
            break;
          case 4:
            aReportar[3] = x.duracion;
            break;
        }
        switch(convenio.quinto_param_archivo){
          case 0:
            aReportar[4] = x.fecha;
            break;
          case 1:
            aReportar[4] = x.cliente.telefono;
            break;
          case 2:
            aReportar[4] = x.telefonoDestino;
            break;
          case 3:
            aReportar[4] = x.duracion*convenio.costo_segundo;
            break;
          case 4:
            aReportar[4] = x.duracion;
            break;
        }
        reporteFinal.push(aReportar);
      }); 
      this.archivoService.downloadReporteFile(reporteFinal,"reporte"+convenio.nit,convenio);
    });
  }
}
