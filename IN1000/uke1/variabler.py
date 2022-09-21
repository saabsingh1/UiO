"""
programmet ber om navnene på brukerne, som den
slår sammen. det regner også differansen mellom to variabler
"""
print("Hei student!")
#bruker input for å legge inn navnet på brukern
navn = input("Hva er navnet ditt?: ") 
print("Hei" , navn)
#setter inn to heltall i to variabler
tall = 10
tall1 = 5
print(tall)
print(tall1)
#legger differansen inn i en tredje variabel 
tall2 = tall - tall1
print("Differanse:", tall2)
navn1 = input("Hva er navnet ditt?: ")
#slår navnene sammen i variabel og printer
sammen = navn + navn1
print (sammen)
#ny variable, denne gangen med "og" mellom og mellomrom
sammen = navn + " og " + navn1
print(sammen)
