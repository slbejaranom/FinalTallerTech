import { Component, OnInit } from '@angular/core';
import { EmpresaTelefoniaFijaService } from 'src/app/services/empresa-telefonia-fija.service';
import { MatButtonModule, MatIconModule } from '@angular/material';
import { EmpresaTelefoniaFija } from 'src/app/models/empresaTelefoniaFija';
import { ArchivoService } from 'src/app/services/archivo.service';
import { MatPaginatorModule, MatTableModule } from '@angular/material';
import { Llamada } from 'src/app/models/llamada';
import { LlamadaService } from 'src/app/services/llamada.service';

@Component({
  selector: 'app-convenios',
  templateUrl: './convenios.component.html',
  styleUrls: ['./convenios.component.css']
})

export class ConveniosComponent implements OnInit {

  totalConvenios: number;
  empresaTelefoniaFija: EmpresaTelefoniaFija = new EmpresaTelefoniaFija();
  entradaTexto: string = "";
  llamadasPorEmpresa: Array<Llamada> = new Array();

  constructor(private llamadaService: LlamadaService, private empresaTelefoniaService: EmpresaTelefoniaFijaService, private archivoService: ArchivoService) { }

  ngOnInit() {
    this.empresaTelefoniaService.getCantidadConvenios().subscribe(val => this.totalConvenios = val);
  }
  buscarClicked() {
    if (this.entradaTexto != "") {
      this.empresaTelefoniaService.getConvenioByNit(this.entradaTexto).subscribe((val) => {
        this.empresaTelefoniaFija = val;
        this.llamadaService.getLlamadasPorConvenio(this.entradaTexto).subscribe((res) => {
          this.llamadasPorEmpresa = res;
        });
      },
        (error) => {
          if (error.status == 404) {
            alert("No se encontró empresa con este nit");
            this.empresaTelefoniaFija = new EmpresaTelefoniaFija();
          }
        });
    }else{
      alert("No puede dejar el campo vacío");
    }
  }
  descargarConvenios() {
    this.empresaTelefoniaService.getAllConvenios().subscribe((res) => {
      this.archivoService.downloadFile(res, "convenios");
    })
  }
}
