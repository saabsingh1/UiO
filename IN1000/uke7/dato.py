class Dato: #opretter klassen 
    def __init__(self, nyDag, nyMaaned, nyttAar): #definerer konstruktøren 
        self.nyDag = int(nyDag) #definerer instansvariablene
        self.nyMaaned = int(nyMaaned)
        self.nyttAar = int(nyttAar)

    def hentAar(self): #lager metoden som angir hvilket år datoen er i 
        return "Aaret i datoen er: " + str(self.nyttAar)

    def lagStreng(self): # lager metoden som setter datoen lik en brukervennlig streng 
        return "Dato: " + str(self.nyDag) + "." + " i " + str(self.nyMaaned) + "." + " år " + str(self.nyttAar)

    def sjekkDato(self): #metoden sjekker om datoen er en gitt dag i måneden 
        if self.nyDag == 15:  #bruker if/elif/else setning
            print("Loenningsdag!")
        elif self.nyDag == 1: 
            print("Ny maaned, nye muligheter!")
        else: 
            print("Vanlig kjedelig dag...")





