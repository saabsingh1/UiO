def gitt_ord(et_ord):
    antall = 0 
    for tegn in et_ord:
        antall +=1
    return antall 

print(gitt_ord("Hallaballa"))

def gitt_ordbok(en_setning): 
    tekst = en_setning.split(" ")
    for i in range(len(tekst)):
        tekst[i] = tekst[i].lower()
    tekstordbok = {}
    for ord in tekst: 
        tekstordbok[ord] = tekst.count(ord)
    return(tekstordbok)

print(gitt_ordbok("Denne Denne veldig vanskelige oppgaven var veldig vanskelig"))
