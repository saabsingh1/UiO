fil_maal = open("maal.csv", "w") #lager en ny fil
fil_maal.write("Skulderbredde 4" + "\n" + "Halsvidde 3.2" + "\n" + "Livvidde 10") #skriver inn til filen 
fil_maal.close() #lukker og åpner filen igjen 
fil_maal = open("maal.csv", "r")

ordbok_maal = {} #lager en tom ordbok 
def innlesning(fil_maal): #lager en funksjon 
    for linje in fil_maal: #lager en for løkke, som iritrerer linje for linje 
        biter = linje.rstrip().split(' ') #splitter linjene i filen 
        kroppsdel = biter[0] #setter indeksene til variabler 
        tommer = biter[1]
        ordbok_maal[kroppsdel] = float(tommer) #setter inn de forskjellige variablene som nøkkel - og innholdsverdier
    return ordbok_maal #returnerer ordboka
    
print(innlesning(fil_maal)) # printer ut ordboka

def tommertilcm(ordbok_maal): #lager en prosedyre
    for i in ordbok_maal: #lager en for løkke som iriterer linje for linje
        antallcm = float(ordbok_maal[i] * 2.54) #setter innholdverdiene inn i prosedyren som regner om fra tommer til cm
        print(i, antallcm, "cm") #printer ut resultatene 

tommertilcm(ordbok_maal) #kaller på prosedyren 


