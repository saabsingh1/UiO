#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int stringsum(char* s); 
int distance_between(char* s, char c); 

int stringsum(char *s){
    int i = 0; 
    int diff1; 
    int diff2; 
    int sum = 0; 
    
    for (i = 0; s[i]!= 0; i++){
        int asc = s[i];
        
        if (96 < asc && asc < 123){
            diff1 = asc - 96; 
            sum = sum + diff1; 
        }

        else if(64 < asc && asc < 91){
            diff2 = asc - 64;
            sum = sum + diff2; 
        }

        else if(asc == 32){
            //nothing 
        }

        else{
            return -1;    
        }

        diff1 = 0;
        diff2 = 0;    
    }
    return sum; 
}

int distance_between(char* s, char c){
    int i = 0; 
    int j = (strlen(s)-1); 
    for (i = 0; s[i]!= 0; i++){
        if(s[i] == c){
            break; 
        } 
    }

    for(j;j>=0;j--){
        if(s[j] == c){
           return j - i; 
        }
    }
    return -1; 
}



char* string_between(char* s, char c){
    int forste = 0;
    int siste = 0;
    int counter = 0; 
    int length = strlen(s);
    for (int i = 0; i < length; i++){
        if(s[i] == c){
            forste = i;
            counter++; 
            break; 
        }
    }
    if(counter == 0){
        return NULL; 
    }
    for(int j = length; j>=0 ;j--){
        if (j == forste) {
            char *tom = malloc(sizeof(char)); 
            tom[0] = 0;
            return tom;
        }
        if(s[j] == c){
            siste = j;
            counter++; 
            break; 
        }
    }
    
    int kopistorrelse = siste - forste;
    char *ny = malloc(sizeof(char) * kopistorrelse + 1);

    for (int x = 0; x < siste - 2; x++){
        ny[x] = s[forste + x + 1];  
    }
    
    return ny; 
}

int stringsum2(char* s, int* res){
    int sum = stringsum(s); 
    *res = sum; 
    return 0; 
}