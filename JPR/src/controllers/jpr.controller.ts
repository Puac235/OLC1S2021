import {Request,Response} from 'express';

class rjpController{

    public helloWorld (req:Request,res:Response){
        res.send("Hola Mundo RJP :D");
    }
}   

export const controller = new rjpController();

