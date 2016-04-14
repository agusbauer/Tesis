package tesis;
%%
%class Lexer
%type Integer
%line

TEXT = [A-Za-z_ ][A-Za-z_0-9 ]*

LineTerminator = \r|\n|\r\n
WhiteSpace     = {LineTerminator} | [ \t\f]
InputCharacter = [^\r\n]
TITLE = "###" {TEXT} {InputCharacter}* {LineTerminator}?
BOLD = "*" {TEXT} "*"
ITALIC = "#" {TEXT} "#"

%{
public String lexeme;
%}
%%

{TEXT} {lexeme = yytext();return 0;}
{LineTerminator} {lexeme = yytext(); return -1;}
{TITLE} {lexeme = yytext();return 1;}
{BOLD} {lexeme = yytext(); return 2;}
{ITALIC} {lexeme = yytext();return 3;}
. {lexeme = yytext(); return -3;}