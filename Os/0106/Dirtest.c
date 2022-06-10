#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <dirent.h>
 
int main(int argc, char *argv[])
{
    DIR *dirp;                      // ディレクトリ・ストリーム
    struct dirent *entry;           // ディレクトリのエントリへのポインタ
    char *path = "/mnt/c/work";      // オープンするディレクトリのパス
 



    dirp = opendir(path);           // ディレクトリ /usr/local をオープン
 

    // ディレクトリのオープンに失敗したら終了
    if (dirp == NULL) {
        // エラーメッセージを表示
        printf("ディレクトリ %s がオープンできませんでした\n", path);
        exit(1);     // プログラム終了
    }
 
    // ディレクトリのエントリを1つ読み出す
    entry = readdir(dirp);
 
    // readdir が NULL を返したら，読み出すエントリはない
    while(entry != NULL) {
        // 読み出したエントリの i-node 番号とファイル名を表示
        printf("%ld %s\n", (long)entry->d_ino, entry->d_name);
        entry = readdir(dirp);
    }
 
    closedir(dirp);  // ディレクトリをクローズ
 
    return 0;
    ///mnt/c/work/Os/0106
}