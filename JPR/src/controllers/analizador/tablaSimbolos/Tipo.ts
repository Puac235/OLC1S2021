export default class Tipo
{
    private tipos: tipos;
    
    constructor(tipos: tipos){
        this.tipos = tipos;
    }

    public equals(obj: Tipo){
        return this.tipos == obj.tipos;
    }

    public getTipos():tipos
    {
        return this.tipos
    }

    public setTipo(tipo:tipos)
    {
        this.tipos = tipo;
    }
}

export enum tipos
{
    ENTERO, 
    DECIMAL,
    CARACTER,
    BOOLEANO,
    CADENA
}