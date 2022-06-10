#include <pthread.h>
#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
 
int myglobal = 0;     // スレッド間で共有される変数
int semaphone = 1;
int wait = 0;
//
// main関数とは別のスレッドで実行される関数

void p_Operation(){
    if(semaphone != 0){
        myglobal++;
    }else{
        wait++;
        sleep(10);
    }
}

void v_Operation(int i){
    if(i<10){
        semaphone++; 
        p_Operation();
    }else{
        wait--;
    }
}

//
void *thread_func(void *arg)
{
    int i;
    int temp;
 
    for ( i = 0; i < 10; i++ ) {
        printf("(A)");     // "(A)" を表示
        fflush(stdout);
        /*temp = myglobal;   // 大域変数 myglobal の値を取得
        temp = temp + 1;*/
        p_Operation();
        sleep(1);          // 1秒間スリープ
        v_Operation(i);
        /*myglobal = temp;   // myglobal をインクリメント*/
    }
 
    return NULL;
}
 
//
// main 関数
//
int main(void)
{
    int i;
    pthread_t mythread;     // スレッドの識別子
 
    // スレッド mythread を生成して，実行開始
    if ( pthread_create( &mythread, NULL, thread_func, NULL) ) {
        printf("スレッド生成エラー");
        abort();
    }
 
    for ( i = 0; i < 10; i++ ) {
        printf("(M)");            // "(M)" を表示
        fflush(stdout);
        p_Operation();
        //myglobal = myglobal + 1;  // myglobal をインクリメント
        sleep(1);                 // 1秒間スリープ
        v_Operation(i);
    }
 
    // スレッド mythread の終了を待つ
    if ( pthread_join( mythread, NULL ) ) {
        printf("スレッド終了待ちエラー");
        abort();
    }
 
    printf("\n myglobal = %d\n", myglobal);  // myglobal の値を表示
   
    return 0;
}