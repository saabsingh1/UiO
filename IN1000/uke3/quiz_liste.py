#Dette er et quiz program hvor brukern skal svare på spørsmål 
#og få vite om det er riktig eller galt
#lager en kort intro 
print("________VELKOMMEN TIL QUIZTER!________")
velkomst = input("La oss sette i gang! Trykk på en tast for å fortsette.")
#bare for å få det ryddigere i terminalen 
def linjeskift(): 
    print()
linjeskift() 
#lagrer svarene i en liste 
svar = ["usa", "erna solberg", "egypt"]
#etter at brukeren gir input, tar vi en if sjekk mot lista med riktige svar
sporsmål1 = input("Hvilket land sliter mest med COVID-19 p.d.d?: ").lower()
if sporsmål1 in svar : 
    print ("Riktig svar")
else : 
    print ("Feil svar! Vi fortsetter.")
linjeskift()
sporsmål2 = input("Hva heter Norges statsminister?: ").lower() 
if sporsmål2 in svar : 
    print ("Riktig svar")
else : 
    print ("Feil svar! Vi fortsetter.")
linjeskift()
sporsmål3 = input("I hvilket land er Kairo en hovedstad? ").lower()
if sporsmål3 in svar : 
    print ("Riktig svar")
else : 
    print ("Feil svar! Vi fortsetter.")
    
#i utgangspunktet var tanken å lage to lister, et med fasiten, og en med svar fra bruker
#for å deretter gjøre en if sjekk kun ved bruk av de to listene, og til slutt skulle dette være i en prosdeyre
# det ble for vanskelig og knapt med tid, men tror det i utgangspunktet skal gå an. 
    
print("Takk for i dag!")




