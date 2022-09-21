#har lagt opp til at bruker selv taster inn grader
#ved bruk av input
inntasting = (input("Skriv inn antall grader fahrenheit: "))
#konverterer til integrer, ettersom vi trenger heltall og ikke str
#når vi skal regne om senere ttil celsius
temp_fahrenheit = int(inntasting)
temp_celsius = ((temp_fahrenheit) - 32) * 5/9
print(temp_celsius)

#formelen er riktig, og koden skal nå klare å regne om fra f til c! 
