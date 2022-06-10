//アルゴリズム論　マージ法実行
#include<stdio.h>

void msort(int a[],int b[],int p,int n)
{
    int h;

    if(n>1)
    {
        h = n/2;
        msort(a,b,p,h);
        msort(a,b,p+h,p-h);
        merge(a,b,p,n);
    }
}

void merge(int a[],int b[],int p,int n)
{
    int i,j,k,h;

    h=n/2;
    i=p;
    j=p+h;

    for(k=p;k<p+n;k++)
        if(j == p+n || (i<p+h && a[i]<=a[j]))
            b[k] = a[i++];
        else
            b[k] = a[j++];
    for(k=p;k<p+n;k++)
        a[k] = b[k];
}