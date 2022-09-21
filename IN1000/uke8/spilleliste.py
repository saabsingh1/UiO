from sang import Sang #importerer klassen Sang fra filen sang.py

class Spilleliste: #opretter klassen Spilleliste
    def __init__(self, listenavn): #forhåndsdefinerte konstruktør, instansvariabler og parametere. 
        self._sanger = []
        self._navn = listenavn

    def __str__(self): #lager en str metode som returner en menneskevennlig utskrift av self._sanger
        return self._sanger 

    def lesFraFil(self, filnavn): #opretter metoden som gjør det mulig å lese inn sanger fra en fil
        fil = open(filnavn, "r") #åpner filen, og setter fil som variabel 
        for linje in fil: #lager en for løkke som iritrerer gjennom hver linje i filen
            alleData = linje.strip().split(";") #splitter linjene 
            tittel = alleData[0] #setter indeksen i lista lik variablene tittel og artist
            artist = alleData[1] #ikke nødvendig, men gir meg som programmerer, mer oversikt 
            self._sanger.append(Sang(artist, tittel)) #opretter nye sang objekter, og legger de til den tomme lista self._sanger
        fil.close() #stenger filen til slutt
            
            

    def leggTilSang(self, nySang): #metode for å legge til ny sang til lista
         self._sanger.append(nySang) #bruker append, en vanlig metode for å enkelt legge til noe til en liste 

    def fjernSang(self, sang): #metode for å fjerne en sang fra lista 
         self._sanger.remove(sang) #bruker remove, en metode brukt for lister
    
    def spillSang(self, sang): #lager en metode for å spille av sanger 
        print("Spiller av: ", sang) #en enkel print operasjon, som gjør jobben 

    def spillAlle(self): #metode som spiller av alle sangene i lista 
        for sang in self._sanger: #lager en for løkke som iriterer gjennom self._sanger lista 
            sang.spill() #kaller på metoden fra sang.py, som spiller av en sang, når vi gjør dette kombinert med for løkken, vil alle sangene i lista bli spilt av 

    def finnSang(self, tittel): #metode som finner en oppgitt tittel i lista, og returner denne 
        for sang in self._sanger: #bruker en for løkke som går gjennom lista 
            if sang.sjekkTittel(tittel): #bruker en if test, som kaller på metoden sjekkTittel, med tittel som parameter. denne vil da sammenligne tittelen med sangene i lista 
                return sang #og returnere den første sangen som stemmer overens med parameteren tittel 
        return None #hvis det ikke finnes en tittel som stemmer overens, vil none bli returnert 

    def hentArtistUtvalg(self, artistnavn):
        sanger = [] #lager en tom liste 
        for sang in self._sanger: #iriterer gjennom lista med en for løkke 
            if sang.sjekkArtist(artistnavn): #kaller på metoden sjekkArtist, dersom artistnavnet dukker opp i lista 
                sanger.append(sang) #vil sangen bli lagt inn i lista
        return sanger #returnerer sang 


allMusikk = Spilleliste("Hele musikkbiblioteket")
allMusikk.lesFraFil("musikk.txt")



