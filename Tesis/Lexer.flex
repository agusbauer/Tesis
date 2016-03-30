package tesis;
import static tesis.Token.*;
%%
%class Lexer
%type Token
%line

TEXT = [A-Za-z_ ][A-Za-z_0-9 ]*

LineTerminator = \r|\n|\r\n
WhiteSpace     = {LineTerminator} | [ \t\f]
InputCharacter = [^\r\n]

TITLE = "###" {InputCharacter}* {LineTerminator}?
BOLD = "**" {TEXT} "**" {InputCharacter}* {LineTerminator}?

%{
public String lexeme;
%}
%%

{TITLE} {lexeme = yytext(); return TITLE;}
{BOLD} {lexeme=yytext(); return BOLD;}
{TEXT} {lexeme=yytext(); return TEXT;}
. {return ERROR;}