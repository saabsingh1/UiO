CFLAGS = -std=gnu11 -g -Wall -Wextra

min_vowelshift: vowelshift.c
	gcc $(CFLAGS) vowelshift.c -o vowelshift

strengsum: stringoperations.c tests.c
	gcc $(CFLAGS) stringoperations.c tests.c -o strengsum 

demo: demo.c 
	gcc $(CFLAGS) demo.c -o demo

ificp: 
	scp makefile stringoperations.c tests.c saabprea@login.ifi.uio.no:~/2140/oblig1/

apple: apple.c
	gcc $(CFLAGS) apple.c -o apple