class Hund: #definerer klasssen 
    def __init__(self, alder, vekt): #definerer konstruktøren 
        self.alder = alder
        self.vekt = vekt # opretter og definerer instansvariabler
        self.metthet = 10

    def hentAlder(self): #lager hent metoder som retunerer alder og vekt
        return self.alder

    def hentVekt(self): 
        return self.vekt

    def spring(self): # metode som tar ned metthet og vekt dersom metthet er under 5
        self.metthet -= 1
        if self.metthet < 5: #bruker if setning
            self.vekt -= 1

    def spis(self, mat): # metode som øker metthet med et heltakk og øker vekt dersom metthet overstiger 7 
        self.metthet += mat
        if self.metthet > 7: # bruker if setning 
            self.vekt += 1


