class Bil:
    def __init__(self, eier): 
        self._eier = eier
    
    def skriv_ut(self):
        print(self._eier)

bil1 = Bil("Magne")
bil2 = Bil("Kjell")

bil2.skriv_ut()


class Rektangel: 
    def __init__(self, lengde, bredde):
        self.lengde = lengde
        self.bredde = bredde

    def oekLengde(self, oekning): 
        self.lengde += oekning

    def oekBredde(self, oekning):
        self.bredde += oekning


    def areal(self):
        return self.lengde * self.bredde


    def omkrets(self):
        return self.lengde + self.lengde + self.bredde + self.bredde

    def reduserLengde(self, reduser):
        self.lengde -= reduser

    def reduserBredde(self, bredde):
        self.bredde -= reduser
    
    
rektangel1 = Rektangel(3, 6)
rektangel2 = Rektangel(5, 10)

print(rektangel1.areal())
print(rektangel2.areal())

rektangel1.oekLengde(5)
rektangel2.oekBredde(10)

print(rektangel1.omkrets())
print(rektangel2.omkrets())

