#include "send_packet.h"
#include <sys/select.h>
#include "client_register.h"

#define BUFSIZE 600 

struct klientMld *start = NULL;
struct klientMld{
    char* mld; 
    int sekvensnummer; 
    char* navn; 
    struct klientMld *neste; 
};

struct klientMld *settMld(char* navn, int sekvensnummer, char* mld){
    struct klientMld *tmp = start;
    while(tmp != NULL){
        if(strcmp(tmp->navn, navn) ==0){ 
            tmp -> mld = mld; 
            tmp -> sekvensnummer = sekvensnummer; 
            return tmp;
            
        }
        tmp = tmp->neste;
    }
    
    struct klientMld *klientMld = malloc(sizeof(struct klientMld));

    klientMld -> navn = strdup(navn); 
    klientMld -> sekvensnummer = sekvensnummer; 
    klientMld -> mld = mld; 
    klientMld -> neste = start; 
    start = klientMld;
    return klientMld; 
}

struct klientMld* finnMld(char* navn) {
   struct klientMld* current = start;
   if(current == NULL){
       printf("%s\n", "Denne noden er NULL!"); 
   }
    while (current != NULL){ 
        if (strcmp(current->navn,navn)==0){   
            
            return current; 
        }
        current = current->neste;
    }
    return NULL;
}

void printMLD() { 
    struct klientMld *ptr = start;
    printf("\n[ ");
    while(ptr != NULL){
        ptr = ptr->neste;
    }
    printf(" ]\n");
}
void free_Mld(){
    struct klientMld *current = start; 
    struct klientMld *tmp; 
    while(current != NULL){
        tmp = current;
        current = current ->neste; 
        free(tmp ->navn); 
        free(tmp); 
        
    }
}


int recvtimeout(int s, char *buf, int len, int timeout)
{
    fd_set fds;
    int n;
    struct timeval tv;
    
    FD_ZERO(&fds);
    FD_SET(s, &fds);

    tv.tv_sec = timeout;
    tv.tv_usec = 0;

    n = select(s+1, &fds, NULL, NULL, &tv);
    if (n == 0) return -2; // timeout!
    if (n == -1) return -1; // error

    return recv(s, buf, len, 0);
}

void get_string(char buf[], int size){
    char c; 
    fgets(buf, size, stdin);
    if (buf[strlen(buf) - 1] == '\n')
        buf[strlen(buf) - 1] = 0;
    else
        while((c = getchar()) != '\n' && c != EOF); 
}

void check_error(int res, char *msg){
    if(res == -1){
        perror(msg); 
        /* rydde? */
        exit(EXIT_FAILURE); 
    }   
}

int main (int argc, char const *argv[]){
    if (argc < 5) {
        printf("usage: %s <nick> <adresse> <port> <timeout> <tapssannsynlighet>\n", argv[0]);
        return EXIT_SUCCESS;
    }
    
    const char *nick = argv[1]; 
    const char *adresse = argv[2];
    int port = atoi(argv[3]); 
    //int timeout = atoi(argv[4]); ettersom jeg ikke har implementert timeout skikkelig
    int tapssannsynlighet = atoi(argv[5]); 
    set_loss_probability(tapssannsynlighet);
    int sekvensnummer = 0; 
    printf("UPUSH: VELKOMMEN TIL UPUSH, %s!", nick); 
     

    int fd, wc, rc; 
    fd_set fds; 
    char buf[BUFSIZE]; 
    char *registrering; 
    char buftastatur[BUFSIZE] = " "; 
    struct sockaddr_in server_addr, my_addr, client_addr; 

    fd = socket(AF_INET, SOCK_DGRAM, 0);
    check_error(fd, "socket");  

    my_addr.sin_family = AF_INET; 
    my_addr.sin_port = htons(0); 
    my_addr.sin_addr.s_addr = INADDR_ANY; 
    
    rc = bind(fd, (struct sockaddr *)&my_addr, sizeof(struct sockaddr_in));
    check_error(rc, "bind"); 
    
    server_addr.sin_family = AF_INET; 
    server_addr.sin_port = htons(port);
     
    wc = inet_pton(AF_INET, adresse, &server_addr.sin_addr.s_addr);
    check_error(wc, "inet_pton"); 
    if(!wc){
        fprintf(stderr, "Invalid IP adress: %s\n", adresse); 
        return EXIT_FAILURE; 
    }
    
    asprintf(&registrering, "PKT %d REG %s\n", sekvensnummer, nick );
     
    wc = send_packet(fd, registrering, strlen(registrering), 0, (struct sockaddr*)&server_addr, sizeof(struct sockaddr_in));
    check_error(wc, "sendtoREG"); 
    registrering[wc] = 0;
    
    free(registrering); 
    
    rc = recvtimeout(fd, buf, BUFSIZE -1, 10);// legg inn timeout senere
    check_error(rc, "recv"); 

    buf [rc] = 0; 
    

    if (rc == -1) {
        // error occurred
        perror("En feil har skjedd");
        printf("%s\n", "rc == -1"); 
    }
    else if (rc == -2) {
        //timeout har skjedd
        perror(" TIMEOUT "); 
        printf("%s\n", "rc == -2"); 

    } else {
        printf("UPUSH: DU ER NÅ REGISTRERT!"); 
        buftastatur[0] = 0; 
        while(strcmp(buftastatur, "QUIT") ) {

            
            FD_ZERO(&fds); 
            FD_SET(fd, &fds); 
            FD_SET(STDIN_FILENO, &fds); 
            

            rc = select(FD_SETSIZE, &fds, NULL, NULL, NULL);
            check_error(rc, "select"); 
             
            
            if (FD_ISSET(fd, &fds)) {
                // En mld fra nettet
                // 1 lese mld inn i et buffer 
                // 2 printe bufferet 
                rc = read(fd, buf, BUFSIZE - 1); 
                check_error(rc, "read"); 
                buf[rc] = 0; 
                printf( "\r%45s [ANO] \n", buf); 
                char *tokenArray [BUFSIZE]; 
                tokenArray[0] = 0; 
                char *token;
                int i = 0;  
                char * reply;
                token = strtok((char *)buf, " "); 
                while (token != NULL){
                    tokenArray[i] = token; 
                    
                    token = strtok (NULL, " ");
                    i++; 
                }
                
                if(strcmp(tokenArray[0], "PKT") == 0 ){
                    if(strcmp(tokenArray[2], "FROM") == 0 
                    && strcmp(tokenArray[4], "TO") == 0 
                    && strcmp(tokenArray[6], "MSG") == 0){
                        if(strcmp(tokenArray[5], nick) == 0){
                            printf("%s : %s\n", tokenArray[3], tokenArray[7]); 
                            asprintf(&reply, "ACK %s OK\n", tokenArray[1] );
                            rc = send_packet(fd, reply, strlen(reply), 0, (struct sockaddr*)&server_addr, sizeof(struct sockaddr_in)); 
                            check_error(rc, "sendto ACKOK RIGHTFORMAT/NAME");
                            free(reply); 
                        }
                        else{
                            
                            asprintf(&reply, "ACK %s WRONG NAME\n", tokenArray[1] );
                            rc = send_packet(fd, reply, strlen(reply), 0, (struct sockaddr*)&server_addr, sizeof(struct sockaddr_in)); 
                            check_error(rc, "sendto WRONGNAME");
                            free(reply); 
                        }
                    }
                    else{
                        
                        asprintf(&reply, "ACK %s WRONG FORMAT\n", tokenArray[1] );
                        rc = send_packet(fd, reply, strlen(reply), 0, (struct sockaddr*)&server_addr, sizeof(struct sockaddr_in)); 
                        check_error(rc, "sendto WRONGFORMAT");
                        free(reply); 
                        
                    }
                }
                else if(strcmp(tokenArray[0], "ACK") == 0 && strcmp(tokenArray[2], "NICK") == 0){
                    
                    char *navn = tokenArray[3];
                    char * nyAdresse = tokenArray[5]; 
                    int nyPort = atoi(tokenArray[7]); 
                    client_addr.sin_family = AF_INET; 
                    client_addr.sin_port = nyPort;
                    wc = inet_pton(AF_INET, nyAdresse, &client_addr.sin_addr);
                    check_error(wc, "inet_pton"); 
                    struct klientMld* klientMld = finnMld(navn); 
                    char* mld = klientMld ->mld; 
                    settKlient(navn, client_addr.sin_addr, client_addr.sin_port);
                    printKlienter(); 
                    
                    rc = send_packet(fd, mld, strlen(mld), 0, (struct sockaddr*)&client_addr, sizeof(struct sockaddr_in)); 
                     
                    
                }
                tokenArray[0] = 0; 
            }
            

            if (FD_ISSET(STDIN_FILENO, &fds)){
                // mld fra tastatur
                // 1 lese mld inn i et buffer
                // 2 sende mld over nett til friend
                
                get_string(buftastatur, BUFSIZE); 
                if(strcmp(buftastatur, "QUIT") == 0){
                    free_list(); 
                    free_Mld(); 
                    break; 
                }

                char at = buftastatur[0]; 
                
                memmove(buftastatur, buftastatur +1, strlen(buftastatur)); 
                char *navn = strtok(buftastatur, " ");  
                char *oppslag; 
                char *mld = strtok(NULL, "\n"); 
                settMld(navn, sekvensnummer, mld); 
                printMLD(); 
                
                

                if(at == '@'){
                    struct klient *klient = finnKlient(navn);
                    sekvensnummer = sekvensnummer +1; 
                    
                    if(klient == NULL){
                         
                        
                        asprintf(&oppslag, "PKT %d LOOKUP %s\n", sekvensnummer, navn);
                        rc = send_packet(fd, oppslag, strlen(oppslag), 0, (struct sockaddr*)&server_addr, sizeof(struct sockaddr_in));
                         
                        check_error(rc, "sendtoTASTA"); 
                        free(oppslag); 

                    }
                    else{
                        
                        rc = send_packet(fd, mld, strlen(mld), 0, (struct sockaddr*)&client_addr, sizeof(struct sockaddr_in)); 
                         
                        
                    }
                    
                    
                    

                }
            }
        }
    }
    close(fd); 

    return EXIT_SUCCESS; 

}