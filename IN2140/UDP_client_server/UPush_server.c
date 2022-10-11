#include "common.h"
#include "client_register.h"
#include "send_packet.h"

#define BUFSIZE 255
#define IP "127.0.0.1"

void check_error(int res, char *msg){
    if(res == -1){
        perror(msg); 
        /* rydde? */
        exit(EXIT_FAILURE); 
    }   
}

int main (int argc, char const *argv[]){
    if (argc < 2) {
        printf("usage: %s <port> <tapssannsynlighet> \n", argv[0]);
        return EXIT_SUCCESS;
    }

    int tapssanssynlighet = atoi(argv[2]); 
    set_loss_probability(tapssanssynlighet); 
    struct in_addr ip_addr; 
    inet_pton(AF_INET, IP, &ip_addr); 

    int port = atoi(argv[1]); 
    int sockfd, rc, wc;
    char buf[BUFSIZE]; 
    struct sockaddr_in serveraddr, clientaddr; 
    socklen_t server_len; 

    sockfd = socket(AF_INET, SOCK_DGRAM, 0);
    check_error(sockfd, "socket"); 

    serveraddr.sin_family = AF_INET; 
    serveraddr.sin_addr = ip_addr;  
    serveraddr.sin_port = htons(port);

    rc = bind(sockfd, (struct sockaddr *)&serveraddr, sizeof(struct sockaddr_in)); 
    check_error(rc, "bind");  
   

    while(1){
        server_len = sizeof(struct sockaddr_in);
        rc = recvfrom(sockfd, (char *) buf, BUFSIZE - 1, 0, (struct sockaddr *) &clientaddr, &server_len); 
        check_error(rc, "recvfrom"); 
        buf[rc] = 0; 
        //printf("BUF: %s\n", buf);
        //printf("klient port: %d\n", clientaddr.sin_port);

        char *reply;
        char  PKT[20], type[20], nick[29]; 
        int nummer; 
        sscanf(buf, "%s %d %s %s", PKT, &nummer, type, nick); 
        
        
        if(strcmp(type, "REG")== 0){

            settKlient(nick, clientaddr.sin_addr, clientaddr.sin_port);
            printf("Registrerer klient: %s\n", nick);
            printKlienter(); 
            char *reply; 
            asprintf(&reply, "ACK %d OK\n", nummer); 
            wc = send_packet(sockfd, reply, strlen(reply), 0, (struct sockaddr*)&clientaddr, sizeof(struct sockaddr_in));
            check_error(wc, "sendtoREG");
            free(reply); 
        }
        
        
        else if(strcmp(type, "LOOKUP")==0){
            struct klient *klient = finnKlient(nick); 
            
            if(klient == NULL){
                asprintf(&reply, "ACK %d NOT FOUND\n", nummer); 
                wc = send_packet(sockfd, reply, strlen(reply), 0, (struct sockaddr*)&clientaddr, sizeof(struct sockaddr_in));
                check_error(wc, "sendtoLOOKUP/NULL");
                free(reply); 
            }
            
            else{
                  
                asprintf(&reply, "ACK %d NICK %s IP %s PORT %d\n", nummer, nick, inet_ntoa(klient->ip), klient->port); 
                printf("Søker opp klient: %s\n", reply); 
                wc = send_packet(sockfd, reply, strlen(reply), 0, (struct sockaddr*)&clientaddr, sizeof(struct sockaddr_in));
                check_error(wc, "sendtoLOOKUP");
                
                free(reply); 
            }
        }

        else{
            asprintf(&reply, "ACK %d WRONG FORMAT\n", nummer); 
            wc = send_packet(sockfd, reply, strlen(reply), 0, (struct sockaddr*)&clientaddr, sizeof(struct sockaddr_in));
            check_error(wc, "sendtoLOOKUP/REG/WRONGFORMAT");
            free(reply); 
        }
        type[0] = 0; 
        
        //TODO: 
        // hvis ikke lookup eller reg ?? -> fiks slik at server sender feilmelding tilbake til klient?
        // hvis allerede registrert -> ertsatt ip og port 
        // sekvensnummere må fikses -> lag en globalteller 
        //
    }
    free_list(); 
    close(sockfd); 
    return EXIT_SUCCESS; 
}