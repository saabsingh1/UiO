#programmet skriver ut 3 ganger hvor brukerne kommer fra
#og hva de heter
#definerer en prosedyre, slik at jeg slipper å skrive de sammme
#kodelinjene om og om igjen.
def hilsen():
    navn = input("Hva er navnet dit?: ")
    bosted = input("Hvor kommer du fra?: ")
    print("Hei," , navn + "!", "Du er fra" , bosted)
#legger inn linjeskift slik at det ser ryddigere ut mellom hilsnene
def linjeskift():
    print()
#kaller på prosedyren 3 ganger, i en ny prosedyre
def hilsen2():
    hilsen()
    linjeskift()
    hilsen()
    linjeskift()
    hilsen()
    linjeskift()
#kjører den nye prosedyren 
hilsen2()



 




