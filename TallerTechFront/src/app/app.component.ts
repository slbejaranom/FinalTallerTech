import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'TallerTechFront';
  homeClicked: boolean = false;
  conveniosClicked: boolean = false;
  llamadasClicked: boolean = false;
  configArchivoClicked: boolean = false;
  
  homeClick(){
    this.homeClicked = true;
    this.conveniosClicked = false;
    this.llamadasClicked = false;
    this.configArchivoClicked = false;
  }
  conveniosClick(){
    this.homeClicked = false;
    this.conveniosClicked = true;
    this.llamadasClicked = false;
    this.configArchivoClicked = false;
  }
  llamadasClick(){
    this.homeClicked = false;
    this.conveniosClicked = false;
    this.llamadasClicked = true;
    this.configArchivoClicked = false;
  }
  configArchivoClick(){
    this.homeClicked = false;
    this.conveniosClicked = false;
    this.llamadasClicked = false;
    this.configArchivoClicked = true;
  }
}
