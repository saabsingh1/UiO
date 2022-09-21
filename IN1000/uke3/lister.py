#lager en liste med tre utvalgte tall
min_liste = [3,5,7]
#bruker append for å utvide lista
min_liste.append(9)
#ved å spesifisere indekstallet bak lista finner vi tallene
print(min_liste[0] , min_liste[2])

#lager en tom liste
liste_navn = []
#utvider lista ved å bruke append, etterfulgt av input for å hente inn navn fra bruker
print("Legg inn 4 navn:")
liste_navn.append(input())
liste_navn.append(input())
liste_navn.append(input())
liste_navn.append(input())

#bruker if for å sjekke om navnet mitt dukker opp i lista
#da er det viktig å bruke in for å sjekke om navnet faktisk ligger i lista
if "saab" in liste_navn:
    print("Du husket meg!")
else: 
    print("Glemte du meg?")


#bruker sum for å legge sammen alle tallene i listenb 
summen = sum(min_liste)
#importerer math for å bruke prod funksjonen, slikat jeg kan multiplisere alle tallene
import math
produkt = math.prod(min_liste)

#lager en liste for summen og produktet av den gamle lista 
sumprod_liste = [summen, produkt]
#lager en ny liste igjen, slår sammen to andre
ny_liste = min_liste + sumprod_liste
print(ny_liste)

#sletter de to siste elemente 
del ny_liste [4]
del ny_liste [5]
print(ny_liste)


