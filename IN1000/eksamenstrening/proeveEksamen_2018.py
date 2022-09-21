#oppgave 1a)
tall = 8 

#1b) 

tekst = "acb"

#1c) 

j = 8

#1d) 

#= 30 

#1e) 

#= 7 

#1f) 

#= 22

#oppgave 2 a) 

#= 5

#2b) 

10, 8, 7

#2c) 
6,6,60,5


#3a) 

def penger(femkroninger, kronestykker):
    return femkroninger * 5 + kronestykker 

#3b) 

def barnMedVoksen(alder1, alder2): 
    if alder1 >= 18 and alder2 < 18:
        return True
    elif alder1 < 18 and alder2 >= 18:
        return True
    else: 
        return False
    

#3c) 
#tekstoppgave 

#3d) 

def fyllTilTi(tallene):
    while len(tallene) != 10:
        tallene.append(0)
    return tallene 

print(fyllTilTi([1,2,3,4]))


#3e og 4)
# ork


#4a) 

class Hytte:
    def __init__(self, navn, senger, pris):
        self._navn = navn 
        self._senger = senger 
        self._pris = pris

    def hentNavn(self):
        return self._navn 

    def totPris(self, antall):
        return antall * self._pris
   
    def sjekkPlass(self, antall):
        if antall =< self._senger:
            return True
        else: 
            return False 

    def __str__(self):
        return "Hyttas navn:", self._navn, "\n", "Antall sengeplasser:", self._senger, "\n", "Pris per sengeplass:", self._pris

    def __eq__(self, annen):
        if self._navn == annen.hentNavn():
            return True
        else: 
            return False 


class Tur: 
    def __init__(self, liste, tekst):
        self._liste = liste
        self._tekst = tekst 

    def skrivTur(self):
        print(self._tekst)
        for hytter in self._liste:
            print(hytter)

    def sjekkPrisPlass(self, antall, maksbeløp):
        for hytter in self._liste: 
            if (hytter.sjekkPlass(antall) <= antall) and (hytter.totPris(antall) <= maksbeløp):
                return True 
            else:
                return False 


class Turplanlegger: 
    def __init__(self, hyttefil, turfil):
        self._hytter = self._hytterFraFil(hyttefil)
        self._turer= self._turerFraFil(turfil)

    def _hytterFraFil(self, filnavn): 
        fil = open(filnavn, "r")
        hytter = {} 
        for linje in fil: 
            biter = linje.strip.split()
            hytte = Hytte(biter[0],int(biter[1]),float(biter[2]))
            hytter[biter[0]] = hytte
        return hytter 
        fil.close()

    def _turerFraFil(self, filnavn): 
        fil = open(filnavn, "r")
        liste = []
        
            






