#include <pthread.h>
#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
 
//
// 生成されたスレッドが実行する関数
//
void *thread_func(void *arg)
{
    int i;
     
    for (i = 0; i < 10; i++) {
        printf("(A)");     // "(A)" を表示
        fflush(stdout);
        sleep(1);          // 1秒間スリープ
    }
 
    return NULL;
}
 
//
// main 関数
//
int main(void)
{
    pthread_t mythread;     // スレッドの識別子
    int i;
 
    // スレッド mythread を生成して，実行開始
    if (pthread_create( &mythread, NULL, thread_func, NULL)) {
        printf("スレッド生成エラー");
        abort();
    }
 
    // ここから元のスレッド(mainスレッド)と新たに生成されたスレッドが並行動作する
 
    for (i = 0; i < 5; i++) {
        printf("(M)");    // "(M)" を表示
        fflush(stdout);
        sleep(1);         // 1秒間スリープ
    }
 
    // スレッド mythread の終了を待つ
    if (pthread_join(mythread, NULL)) {
        printf("スレッド終了待ちエラー");
        abort();
    }
 
    printf("\n");
   
    return 0;
}