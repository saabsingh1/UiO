class Person: #definerer klassen
    def __init__(self, navn, alder): #definerer konstruktøren 
        self._navn = navn #
        self._alder = alder #definerer instansvariablene og setter den ene lik en tom ordbok 
        self._hobbyer = []

    def leggtilHobbyer(self, hobby): 
        self._hobbyer.append(hobby) # metode som legger til hobbyer til lista med tom ordbok 
        
    def skrivHobbyer(self):
        print(self._hobbyer) #metode som printer ut lista over hobbyer 

    def skrivUt(self): #metode som skriver ut navn, alder og hobyyer 
        hobby = input("Skriv inn en hobby: ") #input som tar inn hobbyer 
        for i in hobby: #lager en løkke 
            self._hobbyer.append(hobby) #legger hobby til lista 
            hobby = input("Vil du legge til en ny hobby? Hvis ikke, tast 's':" ) #looper inputen 
            if hobby == "s": #opretter mulighet for å hoppe ut
                break
        print(self._navn) #printer ut navn, alder og liste med hobbyer
        print(self._alder)
        skrivHobbyer()






