def adder (tall1, tall2):
    return (tall1 + tall2)

summen = adder(3, 4)
print(summen)
summen = adder(2, 8)
print(summen)

#SE OVER IMORGEN deloppgave a)

tekst = input("skriv inn en tekststreng her: ")
antall = tekst.count(input("skriv inn en bokstav her: "))
print(antall, "gang(er) forekommer denne bokstaven i tekststrengen")


def tellforekomst (mintekst, minbokstav): 
    forekomst = 0
    for tegn in mintekst:
        if tegn == minbokstav:
            forekomst = forekomst +1 
    return forekomst

tekst1 = input("Skriv inn en tekststreng her: ")
bokstav1 = input("Skriv inn en bokstav her: ")
print(tellforekomst(tekst1, bokstav1), "gang(er) forekommer denne bokstaven i tekststrengen")




