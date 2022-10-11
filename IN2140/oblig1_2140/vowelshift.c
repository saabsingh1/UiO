#include <stdio.h>
char ch;
char str; 

void byttVokal(char *buf, char *ch);

int main(int argc, char *argv[]){

    char *str = argv[1];
    char *ch = argv[2];
    
    byttVokal(str, ch); 
 
    printf("%s\n", str);
    return 0;
    
}

void byttVokal(char *str, char *ch) {

    int i = 0;
    for(i=0; str[i]!='\0'; i++)
    {
        if(str[i]=='a' || str[i]=='e' || str[i]=='i' || str[i]=='o'
           || str[i]=='u' || str[i]=='A' || str[i]=='E' || str[i]=='I'
           || str[i]=='O' || str[i]=='U')
        {
            str[i] = *ch;
        }
    }
}
