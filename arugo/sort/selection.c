//アルゴリズム論　選択法C言語実行
#include<stdio.h>

void selection(int a[],int n)
{
    int i,j,min;
    for(j=1;j<=n-1;j++){
        min=j;
        for(i=j+1;i<=n;i++){
            if(a[min]>a[i]){
                swap(&a[j],&a[min]);
            }
        }
    }
}

//swap関数は場所を交換する関数