#definerer prosdeyren
#bruker int for at inputen skal konverteres til integer
def pris ():
    alder = int(input("Hva er alderen din?: "))
    billettpris = 0

    if alder <= 17:
        billettpris = 30
    
    elif 63 > alder > 17:
        billettpris = 50

    elif alder <= 63: 
        billettpris = 35 

    print(billettpris , "kr" , "koster billetten")

def linjeskift():
    print()

pris()
linjeskift()
pris()
linjeskift()
pris()


#her la ikke oppgaven opp til at man skulle avgrense 63 og 17, det ble derfor 
#printet ut feil pris. jeg har løst oppgaven slik at riktig pris bli printet ut. 
