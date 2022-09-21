"""
dette er et program som summerer, subrakterer og dividerer to tall med input fra brukeren 
den kan også regne om tommer til cm med input fra bruker
"""

#lager en prosedyre som regner ut summen 
#av to paramtere, for å så returnere summen til funksjonen
def addisjon(tall1, tall2):
    return (tall1+tall2) # returnerer summen
print(addisjon(3,4)) #printer


def subtraksjon(tall1, tall2):
    return (tall2-tall1)

assert subtraksjon(2,5) == 3 #bruker assert for å sjekke at 
assert subtraksjon(-4, -3) == 1 #kallet med forskjellige argumenterer
assert subtraksjon(5, -5) == -10 #får verdien jeg forventer

def divisjon(tall1, tall2): 
    return (tall1/tall2)

assert divisjon(4, 2) == 2
assert divisjon(-10, -2) == 5
assert divisjon(-6, 2) == -3

def tommertilcm(antalltommer): # lager en prosdeyre 
    assert antalltommer > 0 # bruker assert for å sjekke at antalltommer er større enn 0 
    antallcm = float(antalltommer * 2.54)
    return antallcm #returnerer til funksjonen 

print (tommertilcm(2))

def skrivberegninger():  #lager en prosdeyre
    tall1 = float(input("Skriv inn et tall: ")) #setter inputene i prosedyrene
    tall2 = float(input("Skriv inn et tall: ")) # legger de i variablene tall1, tall 2
    print()
    print("Resultat av summering: ", addisjon(tall1, tall2)) #når jeg nå kaller på og printer ut funksjonene
    print("Resultat av subtraksjon: ", subtraksjon(tall1, tall2)) #bruker jeg alltid inputene som argumenter
    print("Resultat av divisjon: ", divisjon(tall1, tall2)) #da får jeg riktig utregning og sammenheng i koden
    print()
    tommerinput = float(input("Skriv inn hvor mange tommer du vil regne om: "))
    print("Antall cm: ", tommertilcm(tommerinput))

skrivberegninger() # kaller på prosedyren med alle funksjonene inne 