#include <sys/types.h>
#include <sys/stat.h>
#include <time.h>
#include <stdio.h>
#include <stdlib.h>
 
int main(int argc, char *argv[])
{
    // ファイルの属性を格納する構造体
    struct stat statBuf;
 
    // コマンドライン引数の個数をチェック
    if (argc != 2) {
        printf("パスを指定してください\n");
        exit(1);
    }
     
    // ファイルの属性を取得
    if (stat(argv[1], &statBuf) == -1) {
        // stat の呼び出しに失敗
        perror("statの呼び出しに失敗しました");
    exit(0);
    }
 
    // 属性の表示
    printf("所有者のユーザID: %ld\n", (long)statBuf.st_uid);
    printf("ファイルサイズ: %ld\n", (long)statBuf.st_size);
    printf("i-node番号: %ld\n", (long)statBuf.st_ino);
    printf("最終変更時刻:     %s\n", ctime(&statBuf.st_mtime));
            // 関数 ctime() は time_t型のカレンダー時刻を文字列に変換する
    return 0;
}