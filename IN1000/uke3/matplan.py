#lager en ordbok, der måltidene er sortert i lister som innholdsverdi
måltider = {
    "Prabh" : ["toast", "pannekaker", "pizza"], 
    "Oscar" : ["kebab", "burger", "pizza"], 
    "Alex": ["yoghurt", "salat", "kylling"], 
}
#lager en prosedye der nøkkelverdiene blir printet, og en if sjekk 
#som printer ut innholdsverdiene til valgte beboer
def alle_beboere() :
    print("Beboere: " , måltider.keys())

    velg_beboer = input("Skriv inn navnet på beboeren du ønsker matplanen til: ")
    if velg_beboer in måltider.keys():
        print(måltider[velg_beboer])

    else : 
        print("Denne beboeren er ikke registrert")

    
alle_beboere()
"""
a) her ville jeg nok ha brukt en liste, ettersom det er mulig å sortere etter feks alfabetsik rekkefølge og 
bruke indeksene til å kunne hente fram forskjellige brukernavn i lista. her ville mengder vært uaktuelt, mens ordbøker ikke gir mening ettersom man 
ikke trenger å lagre to og to verdier 

b) her ville jeg definitvt brukt ordbok, ettersom det er to verdier som skal lagres mot hverander (brukernavn og poengsum)
ulempen er at det ikke er mulig å sortere, slik som lister. 

c) stort sett samme situasjon som a), ville valgt liste pga de samme fordelene 

d) her kan man fint bruke mengde, ettersom det ikke spiller noen rolle hvilken rekkefølge det kommer i, 
heller ikke om den samme allergien ikke kan vises fler ganger osv. dersom allergien er i mengden, har det ikke
noe å si om den kan sorteres, lagres flere ganger osv. det kritiske i en sånn situasjon er jo bare om den er der eller ikke. 

