import {Request,Response} from 'express';

class rjpController{

    public helloWorld (req:Request,res:Response){
        res.send("Hola Mundo RJP :D");
    }
    public interpretar (req:Request,res:Response){
        res.json({msg:"Entró al método de interpretar :D"});
    }
    
}   

export const controller = new rjpController();

