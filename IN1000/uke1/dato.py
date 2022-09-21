"""
dette er et program som kan fortelle hvilke 
av to datoer som kommer først på året
"""
print("Hei! Legg inn datoene du vil teste med heltall!")
print("Legg først inn dag, deretter måned. Bruk alltid to sifre.")
# definerer variablene, holder det kort og enkelt
dato1 = input("Dag: ")
dato2 = input("Måned: ")
dato3 = input("Dag: ")
dato4 = input("Måned: ")
"""
Løst på papir, hvor jeg har satt meg ned og skrevet noen
forskjellige kombinasjoner av datoer man kan sette opp 
og hva utfallet blir. 
Har brukt "and" her for å korte ned antall kodelinjer.
Bruker if først, og derettter elif og else for å teste
forskjellige rekkefølger.
"""
if dato1 < dato3 and dato2 < dato4:
    print("Riktig rekkefølge")
elif dato1 > dato3 and dato2 < dato4:
    print("Riktig rekkefølge")
elif dato1 < dato3 and dato2 == dato4:
    print ("Riktig rekkefølge")
elif dato1 == dato3 and dato2 == dato4:
    print ("Samme dato!")
else : 
    print("Feil rekkefølge!")

