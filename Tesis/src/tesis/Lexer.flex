package tesis;
import static tesis.Token.*;
%%
%class Lexer
%type Token
L = [a-zA-Z_ ]
D = [0-9]
WHITE=[ \t\r\n]
%{
public String lexeme;
%}
%%
{WHITE} {/*Ignore*/}
"###" {lexeme="<h2>"; return TITLE;}
"**" {return NEGRO;}
{L}({L}|{D})* {lexeme=yytext(); return ID;}
. {return ERROR;}