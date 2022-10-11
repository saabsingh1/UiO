#ifndef CLIENT_REGISTER
#define CLIENT REGISTER 

#include "common.h"

struct klient{
    struct in_addr ip; 
    in_port_t port; 
    char* navn; 
    struct klient *neste; 
};

struct klient *settKlient(char* navn, struct in_addr ip, in_port_t port); 
void printKlienter(); 
struct klient* finnKlient(char* navn); 
void free_list(); 
#endif