steder = []
for destinasjon in range(5): 
    destinasjon= input("Skriv inn et reisemaal: ")
    steder.append(destinasjon)

klesplagg = []
for plagg in range(5):
    plagg = input("Skriv inn et klesplagg: ")
    klesplagg.append(plagg)

avreisedatoer = []
for dato in range(5):
    dato = input("Skriv inn en dato: ")
    avreisedatoer.append(dato)

reiseplan = [] 
reiseplan.extend((steder, klesplagg, avreisedatoer))

for liste in reiseplan: 
    print(liste)

i1 = int(input("Skriv inn et tall fra 0-2: "))
i2 = int(input("skriv inn et tall fra 0-4: "))

if (0 <= i1 <= 2) and (0<= i2 <= 4): 
    print(reiseplan[i1][i2])
else: 
    print("inputen er ikke gyldig")


