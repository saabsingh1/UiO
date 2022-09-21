class Sang: #opretter klassen 
    def __init__(self, artist, tittel): # opretter konstruktøren
        self._artist = artist #opretter instansvariabler
        self._tittel = tittel

    def __str__(self): #legger til en str metode for å får en vennlig utskrift
        return self._artist + ";" + self._tittel #returnerer utskriften vi vil ha 

    def spill(self): #metode som spiller av sanger 
        print("Spiller av: " + self._artist + ";" + self._tittel)

    def sjekkArtist(self, navn): #metode som sjekker parameter navn mot instansvariablen _artist
        navn = navn.lower().split() #splitter opp navn ettersom ett eller flere av navnene skal sjekkes
        for i in navn: #lager en løkke som eritrerer gjennom self._artist
            if i in self._artist.lower().split(): 
                return True #returnerer true dersom navnene i strengen navn, også er i self._artist
        return False #hvis ikke returneres false 
        
    def sjekkTittel(self, tittel): #metode som sjekker om parameter tittel er den samme som i self._tittel
        tittel = tittel.lower().split() #splitter og bruker lower() ettersom den ikke skal skille mellom små/store bokstaver og også kunne finne likheter i navn mellom tittel og instansvariabelen.
        for i in tittel: #bruker også her en for each løkke
            if i in self._tittel.lower().split(): #iritrerer gjennom instansvariabelen
                return True #returner true om løkken er oppfylt
        return False #hvis ikke false

    def sjekkArtistOgTittel(self, artist, tittel): #denne metoden gjør den samme jobben som sjekkArtist og sjekkTitttel, men begge må være oppfylt for å returnere True
        artist = artist.lower().split() #gjør som i metodene over og splitter og bruker lower(). 
        tittel = tittel.lower().split()
        for i in artist: #her lager jeg en for each løkke 
            if i in self._artist.lower().split(): #og en if test 
                for i in tittel: #som gjør at begge løkkene kjøres dersom den første er oppfylt, og dermed må begge løkkene være oppfylt
                    if i in self._tittel.lower().split(): 
                        return True #for at true skal kunne returneres. 
        return False #hvis ikke, returneres false 
    

