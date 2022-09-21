"""
1) Koden vil ikke være lovlig ettersom det inni parentesen ikke er lovlig/riktig bruk av «+». 
Her er heltallet konvertert til en integrer ved bruken av int i variabel b. 
Det går ikke an å bruke + mellom en str og en variabel som er et integrer, ettersom python vil tro at man prøver å summere sammen et heltall og tekst.
Her hadde bruken av « , » printet ut riktig, ettersom dette ikke er en kommando som summerer tall, str osv. 
"""
a = input("Tast inn et heltall! ")
b = int(a)
if b < 10: 
    print (b + "Hei!")

#2)	Blant problemene vi kan møte på når vi kjører denne koden, har vi den åpenbare feilkodingen nevnt overfor, 
#men også noen praktiske problemer. Koden har kun tilrettelagt for input av tall med et siffer (altså under 10), 
# og vil ikke respondere dersom man skriver inn 10 eller noe over. 

