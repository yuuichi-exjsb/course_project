//アルゴリズム論　バブルソート　実行
void bubblesort(int a[],int n)
{
    int i,j,sorted;

    j=n;
    do{
        sorted = 1;
        j = j-1;
        for(i=1;i<=n;i++){
            if(a[i]>a[i+1]){
                swap(&a[i],&a[i+1]);
                sorted = 0;
            }
        }
    }while (!sorted);
    
}