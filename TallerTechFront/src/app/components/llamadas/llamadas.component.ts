import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { LlamadaService } from 'src/app/services/llamada.service';
import { MatPaginator, MatSlideToggleModule, MatTableDataSource } from '@angular/material';
import { Llamada } from 'src/app/models/llamada';

@Component({
  selector: 'app-llamadas',
  templateUrl: './llamadas.component.html',
  styleUrls: ['./llamadas.component.css']
})
export class LlamadasComponent implements OnInit, AfterViewInit {

  constructor(private llamadaService: LlamadaService) { }

  displayedColumns: string[] = ['idLlamada', 'fecha', 'telefonoOrigen', 'telefonoDestino', 'duracion', 'fueReportada'];
  totalLlamadas: number;
  activado: boolean = false;
  opcionSeleccionada: number = 1;
  fechaInicio: Date;
  fechaFin: Date;
  dataSource = new MatTableDataSource<Llamada>();
  duracionBuscada: number;
  llamadas: Array<Llamada> = new Array();

  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;

  ngOnInit() {
    this.llamadaService.getLlamadas().subscribe(llamadas => {
      this.totalLlamadas = llamadas.length;
      this.llamadas = llamadas;
    });
    this.fechaInicio = new Date();
    this.fechaFin = new Date();
  }

  ngAfterViewInit() {
    setTimeout(() => {
      this.dataSource.data = this.llamadas;
      this.dataSource.paginator = this.paginator;
    });
  }

  pasarPagina() {
    if (this.paginator.getNumberOfPages() == (this.paginator.pageIndex + 1)) {
      this.paginator.firstPage();
    } else {
      this.paginator.nextPage();
    }
  }

  buscarLlamadas() {
    if (this.activado) {
      if (this.duracionBuscada != null && this.duracionBuscada != undefined) {
        if (this.opcionSeleccionada == 1) {
          console.log("ahora entré a la opción mayor");
          this.dataSource.data = this.llamadas.filter(x => (x.fecha >= this.fechaInicio && x.fecha <= this.fechaFin) && (x.duracion > this.duracionBuscada));
        }
        else {
          console.log("Ahora entré a la opción menor");
          this.dataSource.data = this.llamadas.filter(x => (x.fecha >= this.fechaInicio && x.fecha <= this.fechaFin) && (x.duracion < this.duracionBuscada));
        }
      }
      else {
        alert("El campo duración no puede estar vacío si la opción para buscar está encendida");
      }
    }else{
      this.dataSource.data = this.llamadas.filter(x => x.fecha >= this.fechaInicio && x.fecha <= this.fechaFin);
    }  
  }
}