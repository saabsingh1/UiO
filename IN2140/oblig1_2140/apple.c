#include <stdio.h>
#include <string.h>

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



int locateworm(char* apple){
    int length = strlen(apple); 
    for(int i = 0; i < length; i++ ){
        if (apple[i] == 'w'){
            return i; 
        }
    }
    return 0; 
}

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
    
    if(counter == 0){
        return 0; 
    }
}

int main(void){
    
    int result =locateworm(apple); 
    printf("%i\n", result); 

    int length = strlen(apple); 
    char nyApple[length];
    strcpy(nyApple, apple); 

    int result2 = removeworm(nyApple);
    int result3 = removeworm(nyApple); 
    printf("%i\n", result2); 
    printf("%i", result3); 
    
    return 0; 
}