import random #importerer random for å senere bruke metoden randit for å trekke ut tilfeldige tall i metoden generer
from celle import Celle #importerer klassen Celle for å bruke videre i spillebrettet vårt

class Spillebrett: #opretter klassen Spillebrett 
    def __init__(self, rader, kolonner): #konstruktøren med parametere som tar i mot argumenter på hvor mange rader og kolonner man ønsker i brettet
        self._rader = rader #instansvariablene med rader og kolonner
        self._kolonner = kolonner
        self._rutenett = [] #instantvariablen rutenett, med en tom liste foreløpig 

        self._generasjonsnummer = 0 #instansvariablen generasjonsnummer, som skal holde følge på hvilken generasjon cellene i brettet er på 

        for r in range(self._rader): #oppretter rutenettet i konstruktøren, bruker en for løkke
            self._rutenett.append([]) # legger til en til tom liste, i den allerede tomme listen self._rutenett, den blir nå en todimensjonal liste 
            for k in range(self._kolonner): #bruker en til for løkke, for å nå legge til celle objektene i de tomme listene vi oprettet i løkken over 
                self._rutenett[r].append(Celle())

        self._generer() #kaller på metoden generer, for å genere et tilfeldig antall levende celler i første generasjon av brettet 


    def tegnBrett(self): #her opprettes selve brettet, vi har fra før av opprettet et rutenett, lagt til utelukkende døde celler, og generert et tilfeldig antall levende celler. Her slås alt sammen. 
        print("\n" * 3) #printer noen tomme linjer for å gjøre det mer ryddig i outputen. 
        for rad in self._rutenett: #iritrerer gjennom rutenettet 
            for kolonner in rad: #for cellene i rutenettet 
                print(kolonner.hentStatusTegn(), end= " ") #henter status på de nå tilfeldig døde/levende cellene, og returnerer . og O i selve spillebrettet. 
            print("\n")
        
        self.finnAntallLevende() #kaller på metoden for å finne totalt antall levende celler i brettet 
        
        print("Generasjon: ", self._generasjonsnummer, "\n" + "Antall levende celler: ", self.finnAntallLevende()) # en printout for å vise hvilken generasjonsnummer, og hvor mange levende celler som finnes 

    def oppdatering(self): #oprettetr metoden oppdatering, som skal bestemme hvilke celler som skal dø, eller lever videre i neste generasjon av brettet
        skalLeve = [] #opretter to foreløpig tomme lister, som skal huse de døde og levende cellene hver for seg 
        skalDoe = []

        for rad in range(len(self._rutenett)): #iritrerer gjennom radene og kolonnene i brettet, bruker range len for å iriterere gjennom alle celle objektene
            for kolonne in range(len(self._rutenett[rad])):
                sjekkNabo = self.finnNabo(rad, kolonne) #opretter variablen sjekkNabo som skal blir brukt til å sjekke statusen på alle naboene rundt det celle objektet vi er på. Bruker finnNabo metoden for å gjøre dette 

                levendeNaboer = [] #opretter en foreløpig tom liste, som skal inneholde alle levende naboer rundt celle objektet 

                for naboCelle in sjekkNabo: #bruker en for løkke for å iriterere gjennom 
                    if naboCelle.erLevende(): #og finne ut de levende naboene til celle objektet 
                        levendeNaboer.append(naboCelle) #legger så disse til den tomme lista 
                
                celleObjekt = self._rutenett[rad][kolonne] #opretter variablen for å vise til det spesifikke celle objektet vi sjekker naboene til 
                statusHovedCelle = celleObjekt.erLevende() #lager en variabel som sjekker statusen til celleobjektet

                if statusHovedCelle == True: #dersom celleobjektet er levende stiller vi en rekke if tester som bestemmer hvorvidt cellen skal legges til listene med celler 
                    if len(levendeNaboer) < 2 or len(levendeNaboer) > 3: #som skal dø eller leve videre i neste generasjon i henhold til reglene for dette i game of life 
                        skalDoe.append(celleObjekt) #dersom cellen har færre enn 2 eller fler enn 3 levende naboer skal cellen legges til skalDoe listen 
                    
                    if len(levendeNaboer) == 3 or len(levendeNaboer) == 2: #dersom cellen har akkurat 2 eller 3 levende naboer, skal cellen legges til listen skalLeve
                        skalLeve.append(celleObjekt)

                else:
                    if len(levendeNaboer) == 3: # dersom cellen har akkurat 3 levende naboer skal den legges til skalLeve lista
                        skalLeve.append(celleObjekt)

        for celleSamlet in skalLeve: #setter ny status på cellene ved iritere gjennom lista med celler som skal leve eller dø 
            celleSamlet.settLevende() #setter status til levende på cellene som skal det 

        for celleSamlet in skalDoe:
            celleSamlet.settDoed() #setter status til dø på cellene som er i lista for det 

        self._generasjonsnummer += 1 #øker generasjonsnummeret med 1 etter oppdateringen

    def finnAntallLevende(self): #oppretter metoden som skal finne ut hvor mange levende celler det er på brettet  
        antallLevende = 0 #setter telleren til 0 
        for rad in self._rutenett: #iritrerer gjennom rutenettet og hver enkelt celle 
            for kolonne in rad:
                if kolonne.erLevende(): #bruker if test, dersom statusen på cellen er levende 
                    antallLevende += 1 #øker telleren med 1 
        return antallLevende #returnerer totalt antall levende celler på brettet

    def finnNabo(self, rad, kolonne): #oppretter metoden som holder styr på naboene til celle objektet, metoden tar i mot parameterene rad og kolonne 
        sjekkBak = -1 #lagrer intervallet vi sjekker naboene til cellene i (-1, 2) som variabler, gjør det mer oversiktlig for min del 
        sjekkForan = 2
        listeMedNaboer = [] #opretter en foreløpig tom liste med naboer

        for r in range(sjekkBak, sjekkForan): #iriterer gjennom intervallet 
            for k in range(sjekkBak, sjekkForan):
                naboRad = rad + r #setter variablene naborad som rad + r, der er r er intervallet vi sjekker objektet i 
                naboKolonne = kolonne + k #samme her, bare for kolonnene 

                gyldigNabo = True #setter at gyldignabo er true 

                if (naboRad) == rad and (naboKolonne) == kolonne: #dersom naborad og nabokolonne tilsvarer parameterene rad og kolonne, returneres False 
                    gyldigNabo = False #dette fordi vi da sjekker selve celle objektet vi er på 
                
                if (naboRad) < 0 or (naboRad) >= self._rader: #dersom naborad er mindre enn 0, eller større enn antall rader vi gir som argument 
                    gyldigNabo = False #returneres false, dette fordi vi da er utenfor indeksen i lista som er rader, vi er da utenfor selve brettet. 

                if (naboKolonne) < 0 or (naboKolonne) >= self._kolonner: #det samme gjelder for kolonnene, vi kan ikke sjekke naboer som er utenfor brettet
                    gyldigNabo = False #et eksemple på dette er når vi sjekker naboene til en celle som befinner seg i et av hjørnene på brettet. 
                
                if gyldigNabo: # dersom naboene er gyldige
                    listeMedNaboer.append(self._rutenett[naboRad][naboKolonne]) #legger vi til cellene i lista over naboer 

        return listeMedNaboer   #returnerer lista med naboer 

    def _generer(self): #metoden generer opretter et tilfeldig antall levende celler på brettet 
        for rad in self._rutenett: #iritrerer gjennom rutenettet 
            for kolonner in rad:
                randome = random.randint(0,2) #bruker metoden randint, som gir et tilfeldig tall mellom 0-3 på hvert celle objekt. dette gir 33,3% sjanse for at cellen blir levende 
                if randome == 1: #dersom det tilfeldig tallet celle objektet blir tildelt er lik 1 
                    kolonner.settLevende() #setter vi statusen til cellen til levende


