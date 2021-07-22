import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { error } from 'protractor';
import { EmpresaTelefoniaFija } from 'src/app/models/empresaTelefoniaFija';
import { EmpresaTelefoniaFijaService } from 'src/app/services/empresa-telefonia-fija.service';

@Component({
  selector: 'app-crear-convenio',
  templateUrl: './crear-convenio.component.html',
  styleUrls: ['./crear-convenio.component.css']
})
export class CrearConvenioComponent implements OnInit {

  convenio : EmpresaTelefoniaFija = new EmpresaTelefoniaFija();
  periodicidades : number[] = [7, 15, 30];
  parametros : number[] = [0,1,2,3,4];
  //"Fecha","Tel. Origen","Tel. Destino","Valor Llamada","Duración"
  seleccionado : string = "Semanal";
  editar : boolean = true;
  textoBoton : string = "Guardar";

  constructor(private router : Router, private empresaTelefoniaService : EmpresaTelefoniaFijaService) { }

  ngOnInit() {
    this.convenio.fecha_creacion = new Date();
  }

  guardarClicked(){
    if(this.convenio.costo_segundo < 0){
      alert("El costo por segundo no puede ser negativo");
    }else{
       this.empresaTelefoniaService.guardarConvenio(this.convenio).subscribe((val) => {
          console.log(val);
          alert("Convenio creado satisfactoriamente");
       },
       (err)=>{
          if(err.status == 400){
            alert("Revise el formato de los datos y que ningún parámetro se repita")
          }
          if(err.status == 409){
            alert("Ya existe un convenio con ese nit, revise los datos");
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

  volverClicked(){
    this.router.navigateByUrl("Convenios");
  }
}
