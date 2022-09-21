class Motorsykkel: #opretter klassen 
    def __init__(self, merke, regnr, kmstand): #definerer konstruktørenn 
        self.merke = merke
        self.regnr = regnr # definerer instansvariablene
        self.kmstand = kmstand
    
    def kjor(self, km): #metode for å legge til km til kmstanden 
        self.kmstand += km

    def hentKilometerstand(self): #metoden som henter ut total km stand 
        return self.kmstand
    
    def skrivUt(self): #metode som printer ut all info 
        print("Merke: " + self.merke + "\n" + "Registreringsnummer: " + self.regnr + "\n" + "Kilometerstand:", self.kmstand)


