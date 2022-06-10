%{
#include <stdio.h>
#include <math.h>
#define YYSTYPE double
%}
%token NUMBER NEWLINE END
%left  '+' '-'   /* left associative, same precedence */
%left  '*' '/'   /* left assoc., higher precedence */
%%
list:   /* nothing */
        | list expr NEWLINE { printf("Result: %.8g\n", $2); }
        | list END       { return; }
        ;
expr:   NUMBER          { $$ = $1; }
        | expr '+' expr { $$ = $1 + $3; }
        | expr '-' expr { $$ = $1 - $3; }
        | expr '*' expr { $$ = $1 * $3; }
        | expr '/' expr { $$ = $1 / $3; }
        | '(' expr ')'  { $$ = $2; }
        ;
%%
#include "lex.yy.c"
 
int main()
{
    if (yyparse() == 0) {
        printf("Successfully ended\n");
    }
    return 0;
}
 
int yyerror(char *s)
{
    fprintf(stderr, "%s\n", s);
    return 0;
}