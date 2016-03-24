package tesis;
import static tesis.Token.*;
%%
%class Lexer
%type Token
%line

TEXTO = [A-Za-z_ ][A-Za-z_0-9 ]*

LineTerminator = \r|\n|\r\n
WhiteSpace     = {LineTerminator} | [ \t\f]
InputCharacter = [^\r\n]

TITLE = "###" {InputCharacter}* {LineTerminator}?
NEGRITA = "**" {TEXTO} "**" {InputCharacter}* {LineTerminator}?

%{
public String lexeme;
%}
%%

{TITLE} {lexeme = yytext(); return TITLE;}
{NEGRITA} {lexeme=yytext(); return NEGRITA;}
{TEXTO} {lexeme=yytext(); return TEXTO;}
. {return ERROR;}