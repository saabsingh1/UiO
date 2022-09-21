from dato import Dato #importerer dato 

def hovedprogram(): #definerer hovedprogrammet 
    dato1 = Dato(15, 12, 2020) #opretter objektet
    print(dato1.hentAar()) #printer ut året 
    dato1.sjekkDato() #kaller på metoden som sjekker datoen 
    print(dato1.lagStreng()) #printer en brukervennlig utskrift av datoen

hovedprogram() #kaller på hovedprogrammet 