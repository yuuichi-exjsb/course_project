#include <stdio.h>
#include <dirent.h>

int main(void)
{
    DIR *dir;
    struct dirent *dp;
    char dirpath[] = "/tmp";

    dir = opendir(dirpath);
    if (dir == NULL) { return 1; }

    dp = readdir(dir);
    while (dp != NULL) {
        printf("%s\n", dp->d_name);
        dp = readdir(dir);
    }

    if (dir != NULL) { closedir(dir); }

    return 0;
} 
