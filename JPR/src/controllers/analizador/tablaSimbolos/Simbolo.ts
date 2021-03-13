import Tipo from "./Tipo";

export default class Simbolo
{
    private tipo: Tipo;
    private identificador: String;
    private valor: any;

    constructor(tipo: Tipo, identificador: String, valor?:any)
    {
        this.tipo = tipo;
        this.identificador = identificador;
        if(valor)
        {
            this.valor = valor;
        }
        else
        {
            this.valor = null;
        }

    }

    public getIdentificador() {
        return this.identificador;
    }

    public setIdentificador(identificador: String) {
        this.identificador = identificador;
    }
    public getTipo(){
        return this.tipo;
    }
    public setTipo(tipo: Tipo)
    {
        this.tipo = tipo;
    }

    public getValor(){
        return this.valor;
    }
    public setValor(valor: String)
    {
        this.valor = valor;
    }
}