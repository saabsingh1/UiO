ordbok = {}
ordbok = {"olan": "ifi.uio.no", "karian": "student.matnat.uio.no"}


def lagbrukernavn(navn): 
    delt = navn.split(" ")
    fornavn = delt[0]
    etternavn = delt[1]
    brukernavn = (fornavn + etternavn[0]).lower()
    return brukernavn

print(lagbrukernavn("Saab Singh"))


def lagepost(lagbrukernavn, suffix):
    e_post = lagbrukernavn + "@" + suffix
    return e_post



print(lagepost("saabs", "uio.no"))


def printEposter(ordbok):
    for i in ordbok: 
        epost = lagepost(i, ordbok[i])
        print(epost)
         
        
printEposter(ordbok)

ferdig = False

while not ferdig:
    valg = input("Skriv inn en streng, 'i' for å fortsette, 'p' for å skrive ut, eller 's' for å avslutte: ")
    if valg == "i": 
        navn = input("Hva er ditt for - og etternavn?: ").lower()
        suffix = input("Skriv inn en e-post suffix: ")
        brukernavn = (lagbrukernavn(navn))
        ordbok[brukernavn] = suffix
    
    if valg == "p":
        printEposter(ordbok)

    if valg == "s": 
        break 





