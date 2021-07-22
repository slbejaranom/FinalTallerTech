import { Component, OnInit } from '@angular/core';
import { EmpresaTelefoniaFija } from 'src/app/models/empresaTelefoniaFija';
import { EmpresaTelefoniaFijaService } from 'src/app/services/empresa-telefonia-fija.service';
import { CustomPipe } from './CustomPipe';

@Component({
  selector: 'app-config-archivo',
  templateUrl: './config-archivo.component.html',
  styleUrls: ['./config-archivo.component.css']
})
export class ConfigArchivoComponent implements OnInit {

  convenio : EmpresaTelefoniaFija = new EmpresaTelefoniaFija();
  periodicidades : number[] = [7, 15, 30];
  parametros : number[] = [0,1,2,3,4];
  //"Fecha","Tel. Origen","Tel. Destino","Valor Llamada","Duración"
  seleccionado : string = "Semanal";
  editar : boolean = false;
  entradaTexto : string;
  textoBoton : string = "Editar";
  constructor(private empresaTelefoniaService : EmpresaTelefoniaFijaService) { }

  ngOnInit() {
    
  }

  buscarClicked(){
    this.empresaTelefoniaService.getConvenioByNit(this.entradaTexto).subscribe((val)=>{
      this.convenio = val;
    },
    (error)=>{
      if(error.status == 404){
        alert("No se encontró convenio con este nit");
        this.convenio = new EmpresaTelefoniaFija();
      }
    });
  }

  editarClicked(){
    if(this.textoBoton == "Editar"){
      this.editar = true;
      this.textoBoton = "Guardar";
    }
    else{      
      this.empresaTelefoniaService.modificarConvenio(this.convenio).subscribe((val) => {
        console.log(val);
        this.editar = false;
        this.textoBoton = "Editar";
        alert("Guardado satisfactoriamente");
        this.convenio = val;
      }, (err)=>{
        if(err.status == 404){
          alert("No se encontró convenio con este nit");
        }
        if(err.status == 400){
          alert("Revise el formato de los datos que envía y que ningún parámetro de archivo se repita");
        }
      });
    }
  }
  onChange_0(newValue) {
    this.convenio.periodicidad_archivo = newValue;
  }
  onChange_1(newValue) {
    this.convenio.primer_param_archivo = newValue;
  }
  onChange_2(newValue) {
    this.convenio.segundo_param_archivo = newValue;
  }
  onChange_3(newValue) {
    this.convenio.tercer_param_archivo = newValue;
  }
  onChange_4(newValue) {
    this.convenio.cuarto_param_archivo = newValue;
  }
  onChange_5(newValue) {
    this.convenio.quinto_param_archivo = newValue;
  }
}