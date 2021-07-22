import { Pipe, PipeTransform } from "@angular/core";

@Pipe({
    name: "ParamPipe"
})
export class ParamPipe implements PipeTransform {

    transform(n: number) {
        switch (n) {
            case 0:
                return "Fecha";
            case 1:
                return "Tel. Origen";
            case 2:
                return "Tel. Destino";
            case 3:
                return "Valor Llamada";
            case 4:
                return "Duración";
        }
    }
}