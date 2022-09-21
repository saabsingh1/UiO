#lager en ordbok over varer og tilhørende priser 
vare_ordbok = {"melk":14.90, "brod":24.90, "yoghurt":12.90, "pizza":39.90}
print(vare_ordbok)

#utvider ordboka, slik at bruker kan legge inn to vare navn og priser. 
#bruker input for begge deler.
vare_ordbok[input("Skriv inn varenavn: ")] = float(input("Skriv inn prisen: "))
vare_ordbok[input("Skriv inn varenavn: ")] = float(input("Skriv inn prisen: "))
#printer ut ny vare ordbok 
print(vare_ordbok)
