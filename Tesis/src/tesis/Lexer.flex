package tesis;
%%
%class Lexer
%type Integer
%line

TEXT = [A-Za-z_ ][A-Za-z_0-9 ]*

LineTerminator = \r|\n|\r\n
WhiteSpace     = {LineTerminator} | [ \t\f]
InputCharacter = [^\r\n]
 TITLE = "###" {InputCharacter}* {LineTerminator}? | BOLD
HOLA = "$$" {InputCharacter}* {LineTerminator}?
BOLD = "**" {InputCharacter}* {LineTerminator}?

%{
public String lexeme;
%}
%%

{TITLE} {lexeme = yytext(); return 1;}
{HOLA} {lexeme = yytext(); return 2;}
{BOLD} {lexeme = yytext(); return 3;}
. {return -1;}