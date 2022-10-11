CFLAGS = -std=gnu11 -g -Wall -Wextra
BIN = UPush_server UPush_client

all: $(BIN)

UPush_server: UPush_server.c 
	gcc $(CFLAGS) UPush_server.c send_packet.c client_register.c -o UPush_server

UPush_client: UPush_client.c
	gcc $(CFLAGS) UPush_client.c send_packet.c client_register.c -o UPush_client 

clean: 
	rm -f $(BIN)