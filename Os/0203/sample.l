%{
// トークン種別番号の定義
enum { INT = 1, FLOAT, ID, EQ, PLUS, TIMES, SEMI, ERROR, REAL};
%}
%%
int                    { return INT; }
float                  { return FLOAT; }
[A-Za-z]+              { return ID; }
"="                    { return EQ; }
"+"                    { return PLUS; }
"*"                    { return TIMES; }
";"                    { return SEMI; }
[\n\t\s]                 { }
[0-9]+(?:\.[0-9]+)?  { return REAL;}
"//".*                 { }
"/*"(.|[\n])*"*/"       { }        
%%
int yywrap(void) { return 1; }

int main(void)
{
    int token;
 
    while ((token = yylex()) != 0) {
        printf("number = %d, string = '%s'\n", token, yytext);
 
        if (token == ERROR) {
            printf("\tLexical error: '%s'\n", yytext);
        }
        
    }
 
    return 0;
}