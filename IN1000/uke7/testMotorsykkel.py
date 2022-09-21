from motorsykkel import Motorsykkel #importerer hund 

def hovedprogram(): #definerer et hovedprogram 
    motorsykkel1 = Motorsykkel("BMW", "DK2022", 1300) #oprettet 3 motorsykklel objekter 
    motorsykkel2 = Motorsykkel("Yamaha", "LS1234", 2000)
    motorsykkel3 = Motorsykkel("KTM", "AC0824", 3500)

    motorsykkel1.skrivUt() #kalle rpå metoden som printer ut informasjon over objektene 
    motorsykkel2.skrivUt()
    motorsykkel3.skrivUt()

    motorsykkel3.kjor(10) #øker km standen med 10km 
    print(motorsykkel3.hentKilometerstand()) # printer ut ny km stadn 

    motorsykkel3.skrivUt() #kaller på metoden som printer ut ny oversikt over informasjon over objektet 
hovedprogram() #kaller på hovedprogrammet 