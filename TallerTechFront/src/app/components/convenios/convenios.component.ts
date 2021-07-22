import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { EmpresaTelefoniaFijaService } from 'src/app/services/empresa-telefonia-fija.service';
import { MatButtonModule, MatIconModule, MatPaginator, MatTableDataSource } from '@angular/material';
import { EmpresaTelefoniaFija } from 'src/app/models/empresaTelefoniaFija';
import { ArchivoService } from 'src/app/services/archivo.service';
import { Llamada } from 'src/app/models/llamada';
import { LlamadaService } from 'src/app/services/llamada.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-convenios',
  templateUrl: './convenios.component.html',
  styleUrls: ['./convenios.component.css']
})


export class ConveniosComponent implements OnInit{

  textoBoton : string;
  isDisabled : boolean = false;
  totalConvenios: number;
  empresaTelefoniaFija: EmpresaTelefoniaFija = new EmpresaTelefoniaFija();
  entradaTexto: string = "";
  llamadasPorEmpresa: Array<Llamada> = new Array();
  displayedColumns: string[] = ['idLlamada', 'duracion', 'telefonoDestino', 'fueReportada','fecha','cliente'];
  @ViewChild(MatPaginator, {static:false}) paginator: MatPaginator;
  dataSource = new MatTableDataSource<Llamada>(); 

  constructor(private router : Router ,private llamadaService: LlamadaService, private empresaTelefoniaService: EmpresaTelefoniaFijaService, private archivoService: ArchivoService) { }
  
  ngOnInit() {
    this.empresaTelefoniaService.getCantidadConvenios().subscribe(val => this.totalConvenios = val);
  }  

  buscarClicked() {
    if (this.entradaTexto != "") {
      this.empresaTelefoniaService.getConvenioByNit(this.entradaTexto).subscribe((val) => {
        this.empresaTelefoniaFija = val;
        if(this.empresaTelefoniaFija.esta_activo == '0'){
          alert("El convenio está inactivo, no se puede ver información");
          this.textoBoton = "Activar Convenio";
          this.isDisabled = false;
        }else{
          this.llamadaService.getLlamadasPorConvenio(this.entradaTexto).subscribe((res) => {
            this.llamadasPorEmpresa = res;
            this.dataSource.data = this.llamadasPorEmpresa;
            setTimeout(() => this.dataSource.paginator = this.paginator);
            this.isDisabled = true;
            this.textoBoton = "Inactivar Convenio";
          });
        }
      },
        (error) => {
          if (error.status == 404) {
            alert("No se encontró empresa con este nit");
            this.empresaTelefoniaFija = new EmpresaTelefoniaFija();
            this.dataSource.data = [];
            this.isDisabled = false;
          }
        });
    }else{
      alert("No puede dejar el campo vacío");
    }
  }

  pasarPagina(){
    if(this.paginator.getNumberOfPages() == (this.paginator.pageIndex+1)){
      this.paginator.firstPage();
    }else{
      this.paginator.nextPage();
    }
  }

  descargarConvenios() {
    this.empresaTelefoniaService.getAllConvenios().subscribe((res) => {
      this.archivoService.downloadConveniosFile(res, "convenios");
    })
  }

  inactivarConvenio(){
    if(this.textoBoton == "Activar Convenio"){
      this.empresaTelefoniaFija.esta_activo = '1';
    }else{
      this.empresaTelefoniaFija.esta_activo = '0';
      this.isDisabled = false;
    }
    this.empresaTelefoniaService.inactivarConvenio(this.empresaTelefoniaFija).subscribe(val => {
      alert("Estado del convenio cambiado");
      if(this.textoBoton == "Activar Convenio"){
        this.textoBoton = "Inactivar Convenio";
      }else{
        this.empresaTelefoniaFija.esta_activo = '0';
        this.textoBoton = "Activar Convenio";
      }
      this.dataSource.data = [];
    });    
  }

  generarReporte(){
    let reporteFinal = [];
    this.empresaTelefoniaService.generarReporteDeLlamadas(this.empresaTelefoniaFija.nit).subscribe( val => {
      val.forEach( x => {
        let aReportar = {};
        switch(this.empresaTelefoniaFija.primer_param_archivo){
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
            aReportar[0] = x.duracion*this.empresaTelefoniaFija.costo_segundo;
            break;
          case 4:
            aReportar[0] = x.duracion;
            break;
        }
        switch(this.empresaTelefoniaFija.segundo_param_archivo){
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
            aReportar[1] = x.duracion*this.empresaTelefoniaFija.costo_segundo;
            break;
          case 4:
            aReportar[1] = x.duracion;
            break;
        }
        switch(this.empresaTelefoniaFija.tercer_param_archivo){
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
            aReportar[2] = x.duracion*this.empresaTelefoniaFija.costo_segundo;
            break;
          case 4:
            aReportar[2] = x.duracion;
            break;
        }
        switch(this.empresaTelefoniaFija.cuarto_param_archivo){
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
            aReportar[3] = x.duracion*this.empresaTelefoniaFija.costo_segundo;
            break;
          case 4:
            aReportar[3] = x.duracion;
            break;
        }
        switch(this.empresaTelefoniaFija.quinto_param_archivo){
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
            aReportar[4] = x.duracion*this.empresaTelefoniaFija.costo_segundo;
            break;
          case 4:
            aReportar[4] = x.duracion;
            break;
        }
        reporteFinal.push(aReportar);
      }); 
      this.archivoService.downloadReporteFile(reporteFinal,"reporte"+this.empresaTelefoniaFija.nit,this.empresaTelefoniaFija);
    });
  }
  
  redirigirCrearConvenio(){
    console.log("Debería redirigir");
    this.router.navigateByUrl("/CrearConvenio");
  }
}