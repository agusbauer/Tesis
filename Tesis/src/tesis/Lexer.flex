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
"###" {lexeme=yytext(); return TITLE;}
{L}({L}|{D})* {lexeme=yytext(); return ID;}
. {return ERROR;}