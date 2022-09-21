def​ minFunksjon​():
    for​ x ​in​ range​(​2​):
        c ​=​ 2 
    ​   print​(​c) 
        c ​+=​ 1 
        b ​=​ ​10 
        b ​+=​ a 
        ​print​(​b​)
    return​(​b​)

def hovedprogram(): 
    a ​=​ ​42
    b ​=​ 0
    print​(​b​)
    b ​=​ a
    a ​=​ minFunksjon​() 
    print​ ​(​b​)
    print​ ​(​a​)

hovedprogram()

'''
Først defineres funksjonen minFunksjon (), som ikke tar imot noen parametere
Deretter defineres prosedyren hovedprogram(), som ikke tar imot noen parametere heller. 
Så kalles hovedprogrammet. I hovedprogrammet oprettes variabelen a meed verdi 42, 
så variablen b med verdi 0. Etter printen, settes variabelen b lik a, som vil si at verdien nå 
er lik verdien a har, altså 42. variabelen a settes lik minFunksjon() og funksjonen blir nå kalt på. 
I funksjonen blir det brukt en for løkke, hvor x blir satt i range(2), ettersom den ikke blir definert 
videre er verdien til x lik 0. videre blir variabelen c satt lik 2. Så settes det opp til at verdien til c, 
skal bli addert med + 1 i løkka. Videre blir variabelen b i funksjonen satt lik 10. I neste linje i løkka
oppstår det et problem når variabelen b skal bli tilordnet verdien i variabelen a. ettersom a ikke er definert i løkka, 
og kun eksisterer i et lokalt skop i hovedprogram prosedyren, vil man få en feilmelding opp, hvor det står at a ikke er definert (NameError). 
Da stopper koden opp, og vil ikke bli eksekvert videre. 

"""