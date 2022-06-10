//アルゴリズム論　挿入法　実行
#include<stdio.h>

void insertion(int a[],int n)
{
    int i,j,temp;

    for(i=2;i<=n;i++){
        temp = a[i];
        j=i;
        while (j>1 && a[j-1] > temp)
        {
            a[j] = a[j-1];
            j = j-1;
        }
        a[j] = temp;
    }
}