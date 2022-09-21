liste = []
tall = int(input("Tast inn et tall: "))
while tall != 0:
    liste.append(tall)
    tall = int(input("Prøv igjen!: "))

for tall in liste: 
    print(tall)

minsum = 0 
for tall in liste: 
    minsum += tall

print("Summen av tallene er", minsum)  

storste = liste[0]
for tall in liste: 
    if tall > storste:
        storste = tall 

minste = liste[0]
for tall in liste: 
    if tall < minste:
        minste = tall

print("Storste tallet er :", storste)
print("Minste tallet er: ", minste)
       
    