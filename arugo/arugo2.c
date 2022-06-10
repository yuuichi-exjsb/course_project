#include<stdio.h>

void move(char a,char b,char c,int n);
int main()
{
    move('a','b','c',4);
    return 0;
}

void move(char a,char b,char c,int n)
{
    if(n>0){
        move(a,c,b,n-1);
        printf("%d番の円盤を%cから%cに移動する\n",n,a,b);
        move(c,b,a,n-1);
    }
}