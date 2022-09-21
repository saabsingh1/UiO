"""
Dette er et program som spør brukeren om den har
lyst på en brus, brukern vil så få et svar avhengig
av hva de taster inn
"""
brus = input("Kunne du tenke deg en brus? ")
"""
Legger opp spørsmålet til å være ja/nei, 
ettersom programmet krever dette for å gi et svar.
Bruker if test for å kunne gi utskrift. 
"""
if brus == "ja": 
    print("Her har du en brus")
elif brus == "nei":
    print("Den er grei.")
"""
bruker else her, fordi 
programmet ikke vil gi utskrift på noe annet enn ja/nei
"""
else : 
    print("Det forstod jeg ikke helt")
    

