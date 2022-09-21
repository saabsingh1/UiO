from hund import Hund #importerer hund 

def hovedprogram(): #definerer et hovedprogram 
    hund1 = Hund(5, 30) #opretter objektet 

    hund1.spring() #kaller på metoden spring 
    print(hund1.hentVekt()) #printer ut vekten etter å ha kalt på metodene spring og spis
    hund1.spring()
    print(hund1.hentVekt())
    hund1.spis(1)
    print(hund1.hentVekt())
    hund1.spis(1)
    print(hund1.hentVekt())

hovedprogram() # kaller på hovedprogrammet 