import { Cliente } from "./cliente";

export class Llamada{
    idLlamada : number;
    duracion : number;
    telefonoDestino : string;
    fueReportada : string;
    fecha : Date;
    cliente : Cliente;
}