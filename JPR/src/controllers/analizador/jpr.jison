/*
    Organizacion de Lenguajes y Compiladores 1 "A"
    José Puac
    Clase 8
    Jison
*/

/* Definición Léxica */
%lex

%options case-insensitive

%%

"imprimir"           return 'RIMPRIMIR';
";"                 return 'PTCOMA';
"("                 return 'PARIZQ';
")"                 return 'PARDER';

/* Espacios en blanco */
[ \r\t]+            {}
\n                  {}

\"[^\"]*\"				{ yytext = yytext.substr(1,yyleng-2); 	return 'CADENA'; }
[0-9]+("."[0-9]+)?\b    return 'DECIMAL';
[0-9]+\b                return 'ENTERO';

<<EOF>>                 return 'EOF';

.                       { console.error('Este es un error léxico: ' + yytext + ', en la linea: ' + yylloc.first_line + ', en la columna: ' + yylloc.first_column); }
/lex

%{
    const Excepcion = require('./Excepciones/Excepcion');
    const Tipo = require('./tablaSimbolos/Tipo');
    const Arbol = require('./tablaSimbolos/Arbol');
    const Primitivo = require('./Expresiones/Primitivo');
    const Imprimir = require('./Instrucciones/Imprimir');
%}

// PRECEDENCIA

%start INICIO

%% /* Definición de la gramática */

INICIO
	: INSTRUCCIONES EOF {return $1;}
;

INSTRUCCIONES
	: INSTRUCCIONES INSTRUCCION     { $1.push($2); $$ = $1; }
	| INSTRUCCION                   { $$ = [$1]; }
;

INSTRUCCION
	: DEFPRINT          { $$ = $1; }
	| error PTCOMA{ console.error('Este es un error sintáctico: ' + yytext + ', en la linea: ' + this._$.first_line + ', en la columna: ' + this._$.first_column); }
;

DEFPRINT
    : RIMPRIMIR PARIZQ EXPRESION PARDER PTCOMA  { $$ = new Imprimir.default($3, @1.first_line, @1.first_column); }
;

EXPRESION
    : ENTERO { $$ = new Primitivo.default( new Tipo.default(Tipo.tipos.ENTERO),$1, @1.first_line, @1.first_column); }
    | DECIMAL { $$ = new Primitivo.default( new Tipo.default(Tipo.tipos.DECIMAL),$1, @1.first_line, @1.first_column); }
    | CADENA { $$ = new Primitivo.default( new Tipo.default(Tipo.tipos.CADENA),$1, @1.first_line, @1.first_column); }
;
