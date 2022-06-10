void quick(int a[],int p,int q, int *jp,int *ip)
{
    int i,j,s;

    i=p;
    j=q;
    s=a[p];

    while (i<=j)
    {
        while (a[i]<s)
        {
            ++i;
        }
        while (a[j]<s)
        {
            --j;
        }
        if (i<=j)
        {
            swap(&a[i],&a[j]);
            ++i;
            --j;
        }
        *jp = j;
        *ip =i;        
    }
    
}


void qsort(int a[],int p,int q)
{
    int i,j;

    if(p<q){
        quick(a,p,q,&i,&j);
        qsort(a,p,j);
        qsort(a,i,q);
    }
}