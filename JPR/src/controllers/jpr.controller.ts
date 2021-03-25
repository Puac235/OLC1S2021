import {Request,Response} from 'express';
import Excepcion from './analizador/Excepciones/Excepcion';
import Arbol from './analizador/tablaSimbolos/Arbol';
import tablaSimbolos from './analizador/tablaSimbolos/tablaSimbolos';

var Errors:Array<Excepcion> = new Array<Excepcion>();

class rjpController{

    public helloWorld (req:Request,res:Response){
        res.send("Hola Mundo RJP :D");
    }
    public interpretar (req:Request,res:Response){
        var parser = require('./analizador/jpr');

        const text = req.body.entrada;

        try{
            let ast = new Arbol( parser.parse(text) );

            var tabla = new tablaSimbolos();
            ast.setGlobal(tabla);

            for(let m of ast.getInstrucciones()){

                if(m instanceof Excepcion){ // ERRORES SINTACTICOS
                    Errors.push(m);
                    ast.updateConsola((<Excepcion>m).toString());
                }
                var result = m.interpretar(ast, tabla);
                if(result instanceof Excepcion){ // ERRORES SINTACTICOS
                    Errors.push(result);
                    ast.updateConsola((<Excepcion>result).toString());
                }
            }
            res.json({consola:ast.getConsola(), Errores: Errors});
        }
        catch(err){
            console.log(err);
            res.json({
                salida : err,
                errores : err
            });
        }
    }
    
}   

export const controller = new rjpController();

