#include "client_register.h"

struct klient *forste = NULL;
struct klient *settKlient(char* navn, struct in_addr ip, in_port_t port){

    struct klient *tmp = forste;
    while(tmp != NULL){
        if(strcmp(tmp->navn, navn) ==0){
             
            tmp -> ip = ip;  
            tmp -> port = port; 
            return tmp;
            
        }
        tmp = tmp->neste;

    }
    
    struct klient *klient = malloc(sizeof(struct klient));
    klient -> navn = strdup(navn);; 
    klient -> ip = ip; 
    klient ->port = port; 
    klient -> neste = forste; 
    forste = klient; 
    
    return klient; 
}

struct klient* finnKlient(char* navn) {
    struct klient* current = forste;

    if(current == NULL){
       printf("%s\n", "Denne klienten må søkes opp!"); 
    }
    while (current != NULL){    
        if (strcmp(current->navn,navn)==0){   
            printf("\nSøker:\n NAVN: %s\n IP: %s\n PORT: %d\n",current->navn, inet_ntoa(current->ip), current->port);
            return current; 
        }
        current = current->neste;
    }
    return NULL;
}

void printKlienter() { 
    struct klient *ptr = forste;
    printf("\n[");
    while(ptr != NULL) {
        //printf("\nCLIENT REGISTER:\n NAVN: %s\n IP: %s\n PORT: %d\n",ptr->navn, inet_ntoa(ptr->ip), ptr->port);
        ptr = ptr->neste;
    }
    printf("]\n");
}

void free_list(){
    struct klient *current = forste; 
    struct klient *tmp; 
    while(current != NULL){
        tmp = current;
        current = current ->neste; 
        free(tmp ->navn); 
        free(tmp); 
        
    }
}
