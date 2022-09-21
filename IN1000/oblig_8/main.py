from spillebrett import Spillebrett #importerer spillebrettet, trenger ikke importere klassen Celle eller random, ettersom de allerede er importert i klassen spillebrett 

def main(): #oppretter hovedprogrammet
    print()
    print("           ---GAME OF LIFE---", "\n") #printer en introduksjon til game of life 
    print("Vennligst oppgi dimensjonene på brettet: ")
    rader = int(input("Hvor mange rader skal brettet ha?: ")) #lager to variabler med input, slik at bruker kan sende inn antall rader og kolonner som argumenter til parameterne i spillebrett objektet
    kolonner = int(input("hvor mange kolonner skal brettet ha?: "))
    brett1 = Spillebrett(rader, kolonner) #opretter objektet til spillebrett 
    brett1.tegnBrett() #kaller på metoden spillebrett 
    meny = "" # en tom variabel slik at vi kan implementere en while løkke som fungerer som menyen i spillet 
    while meny != "q": # while løkken går så lenge inputen fra brukeren ikke er "q" 
        meny = input("Vil du fortsette til neste steg? Trykk enter, hvis ikke trykk 'q' for å avslutte: ") # her kommer inputen og menyen som lar brukerern velge å fortsette simuleringern av game of life og oppdatere brettet, eller avslutte spillet
        if meny != "q": #dersom brukeren ikke taster inn q
            brett1.oppdatering() #vil brettet oppdateres ved å kalle på metoden på dette 
            brett1.tegnBrett() #og brettet vil deretter tegnes, ved at vi kaller på metoden for det 

        






main() #kaller på hovedprogrammet