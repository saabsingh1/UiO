class Person { //oppretter Person klassen 
    private Bil3 bil; 
    public Person (Bil3 bil) { //her referer jeg til bil3 klassen i konstruktøren til Person 
        this.bil = bil; // selve referansen til bil3 klassen, legger det i en ny variabel bil for videre bruk 
    }



    public void skrivUt() {
        System.out.println ("Bilnr til denne personen er: " + bil.hentNummer()); //bruker hentNummer metoden fra Bil3 for å få bilnr skrevet ut 
    }
} 