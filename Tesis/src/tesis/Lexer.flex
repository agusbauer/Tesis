package tesis;
%%
%class Lexer
%type Integer
%line

TEXT = [A-Za-z_ ][A-Za-z_0-9 ]*

LineTerminator = \r|\n|\r\n
WhiteSpace     = {LineTerminator} | [ \t\f]
InputCharacter = [^\r\n] 
TITLE3 = "###" {TEXT} {InputCharacter}* {LineTerminator}?
TITLE2 = "##" {TEXT} {InputCharacter}* {LineTerminator}?
TITLE = "#" {TEXT} {InputCharacter}* {LineTerminator}?
BOLD = "*" {TEXT} "*"
ITAlIC = "$" {TEXT} "$"
PARAPH = "%" {TEXT} "%"
ITEM = "-" {TEXT} {InputCharacter}* {LineTerminator}?
LISTA = "&" {InputCharacter}* {LineTerminator}? {ITEM}* "&"
ANIDADO = "=" {TEXT} "="

%{
public String lexeme;
%}
%%

{TEXT} {lexeme = yytext();return 0;}
{LineTerminator} {lexeme = yytext(); return -1;}
{TITLE3} {lexeme = yytext(); return 1;}
{TITLE2} {lexeme = yytext(); return 2;}
{TITLE} {lexeme = yytext(); return 3;}
{BOLD} {lexeme = yytext(); return 4;}
{ITAlIC} {lexeme = yytext(); return 5;}
{PARAPH} {lexeme = yytext(); return 6;}
{ITEM} {lexeme = yytext(); return 7;}
{ANIDADO} {lexeme = yytext(); return 8;}
{LISTA} {System.out.println("enronctre lista"); lexeme = "<ul>"+yytext()+"</u>"; return -1;}
. {return -3;}