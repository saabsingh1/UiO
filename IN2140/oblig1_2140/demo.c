#include <stdio.h>
#include <string.h>
#include <stdlib.h>


int distance_between(char *s, char c); 
char* string_between(char* s, char c); 
int removeworm(char* apple);
char* apple = "                     a\n"
              "                    ppl\n"
              "                  eappl\n"
              "                 eapple\n"
              "                 apple\n"
              "                 appl\n"
              "         eappleappleappleappl\n"
              "    eappleappleappleappleapple\n"
              "   appleappleappleappleappleapple\n"
              "  appleappleappleappleappleappleappl\n"
              " eappleappleappleappleappleappleapple\n"
              "appleappleappleappleappleappleappleapp\n"
              "leappleappleappleappleappleappleapplea\n"
              "ppleappleappleappleappleappleappleappl\n"
              "eappleappleappleappleappleappleappleap\n"
              "pleappleappleappleappleappleappleapple\n"
              "appleappleappleappleappleappleappleapp\n"
              "leappleappleappleappwormpleappleapplea\n"
              " ppleappleappleappleappleappleappleap\n"
              "  pleappleappleappleappleappleapplea\n"
              "   ppleappleappleappleappleappleapp\n"
              "   leappleappleappleappleappleap\n"
              "     pleappleappleappleappleapp\n"
              "       leappleappleappleapple\n"
              "         appleappleappleapp\n";

/*char *string_between(char *s, char c){
    int forste = 0;
    int siste = 0;
    int length = strlen(s);
    for (int i = 0; i < length; i++){
        if(s[i] == c){
            forste = i;
            break; 
        } 
    }
    for(int j = length; j>=0 ;j--){
        if(s[j] == c){
            siste = j;
            break; 
        }
    }
    
    printf("%d\n", forste);
    printf("%d\n", siste);
    int kopistorrelse = siste - forste;
    char *ny = malloc(sizeof(char) * kopistorrelse + 1);

    for (int x = 0; x < siste; x++){
        ny[x] = s[forste + x];  
        printf("%c\n", ny[x]);
    }
    
    printf("%s\n", ny);
    return ny; 
}




char *test() {
    char *swag = malloc(sizeof(char) * 5);
    swag[0] = 'c';
    swag[1] = 'a';
    swag[2] = 'r';
    swag[3] = 'l';
    swag[4] = 0;
    printf("%s\n", swag);
    return swag;
}
*/

int removeworm(char* apple){
    int length = strlen(apple);
    int counter = 0; 
    for(int i = 0; i < length; i++){
        if (apple[i] == 'w' || apple[i] == 'o' || apple[i] == 'r' || apple[i] == 'm'){
            apple[i] = ' '; 
            counter ++; 
        }
    }
    return counter; 
}

int main(void){
    //int svar2 = distance_between("yhihihi", 'y'); 
    //printf("%i\n", svar2); 
    //char *svar3 = string_between("PlaceboFylla", 'a'); 
    //test();
    int length = strlen(apple); 
    char nyApple[length];
    strcpy(nyApple, apple); 

    int result2 = removeworm(nyApple);
    printf("%i", result2); 
    
    int result =removeworm(apple); 
    printf("%i", result); 
    

    //printf("%s\n", svar3);
    return 0; 
}
