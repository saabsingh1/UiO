class Celle: #oppretter klasssen celle, som utelukkende er til for å sette og veksle mellom statusene på cellene 
    def __init__(self): #alle cellene er i utgangspunktet døde, og vi setter dette som status til cellen i konstruktøren
        self._status = "doed"

    def settDoed(self): #oppretter metoden som setter statusen til celle objektet til doed 
        self._status = "doed"

    def settLevende(self): #oppretter metoden som setter statusen til celle objektet til levende 
        self._status = "levende"

    def erLevende(self): #metode som returnerer True/False etter hvorvidt cellen har status doed/levende 
        if self._status == "levende": #bruker to if sjekker for å returnere 
            return True
        if self._status == "doed":
            return False 
    
    def hentStatusTegn(self): #bruker if tester for å returnere . eller 0, som på outputen hviser hvorvidt cellen er doed eller levende i spillebrettet vårt. 
        if Celle.erLevende(self) == True:
            return "O"
        if Celle.erLevende(self) == False:
            return "."



