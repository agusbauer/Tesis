<<<<<<< HEAD:Tesis/src/tesis/Lexer.flex
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
=======
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
>>>>>>> parent of f190932... flex file autogeneration in progress:Tesis/Lexer.flex
