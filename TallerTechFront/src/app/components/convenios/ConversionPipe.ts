import { Pipe, PipeTransform } from "@angular/core";

@Pipe({
    name:"Conversion"
})
export class ConversionPipe implements PipeTransform{
    transform(n:number){
        switch(n){
            case 0:
                return "Fecha";
            case 1:
                return "Tel. Origen";
            case 2:
                return "Tel. Destino";
            case 3:
                return "Valor";
            case 4:
                return "Duración";
        }
    }
}