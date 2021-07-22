import { Pipe, PipeTransform } from "@angular/core";

@Pipe({
    name:"CustomPipe"
})
export class CustomPipe implements PipeTransform{

    transform(n:number){
        switch(n){
            case 15:
                return "Quincenal";
            case 30:
                return "Mensual";
            case 7:
                return "Semanal";
        }
    }
}